package pe.martinsam.catalogproducts.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.martinsam.catalogproducts.application.exception.ProductException;
import pe.martinsam.catalogproducts.common.infraestructure.property.MicroserviceProperties;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.dto.CreateProductDto;
import pe.martinsam.catalogproducts.domain.model.product.dto.GetProductDto;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductChangedStatusEvent;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductCreatedEvent;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductDeletedEvent;
import pe.martinsam.catalogproducts.domain.model.product.types.Status;
import pe.martinsam.catalogproducts.domain.repository.ProductRepository;
import pe.martinsam.catalogproducts.infraestructure.events.KafkaProductProducerGeneric;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final KafkaProductProducerGeneric<ProductCreatedEvent> productCreatedProducer;
    private final KafkaProductProducerGeneric<ProductDeletedEvent> productDeletedProducer;
    private final KafkaProductProducerGeneric<ProductChangedStatusEvent> productChangedStatusProducer;


    private final MicroserviceProperties microserviceProperties;

    @Transactional
    public Product addProduct(CreateProductDto productDto) {
        productRepository.findByNameAndSellerId(productDto.name(), productDto.sellerId())
                .ifPresentOrElse(
                        p -> {
                            log.error("Product already exists");
                            throw new ProductException("Product already exists");
                        },
                        () -> log.info("Product not exists"));

        final Product savedProduct = productRepository.save(productDto.toProduct());
        log.info("Product saved: {}", savedProduct);

        productCreatedProducer.sendEvent(
                ProductCreatedEvent.fromProduct(
                        savedProduct,
                        microserviceProperties
                ));
        log.info("Product created event sent: {}", savedProduct.getId());

        return savedProduct;
    }

    public void increaseStock(String id, Integer quantity){
        productRepository.increaseStock(id, quantity);
    }

    public void decreaseStock(String id, Integer quantity){
        productRepository.decreaseStock(id, quantity);
    }

    public Long countProducts() {
        return productRepository.count();
    }

    public Long countProductsBySellerId(String sellerId) {
        return productRepository.countBySellerId(sellerId);
    }

    public List<GetProductDto> getAllProducts() {
        if (productRepository.findAll().isEmpty()) {
            log.error("Products not found");
            throw new ProductException("Products not found");
        }
        return productRepository.findAll().get().stream().map(product -> new GetProductDto(
                product.getId(),
                product.getSellerId(),
                product.getName(),
                product.getBrand(),
                product.getModel(),
                product.getDescription(),
                product.getImageUrl(),
                product.getAdditionalProperties(),
                product.getCurrency(),
                product.getPrice(),
                product.getStock(),
                product.getCategories(),
                product.getTags(),
                product.getIsFreeShipping(),
                product.getStatus().getValue(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        )).toList();
    }

    public GetProductDto getProductById(String id) {
        return productRepository.findById(id).map(product -> new GetProductDto(
                product.getId(),
                product.getSellerId(),
                product.getName(),
                product.getBrand(),
                product.getModel(),
                product.getDescription(),
                product.getImageUrl(),
                product.getAdditionalProperties(),
                product.getCurrency(),
                product.getPrice(),
                product.getStock(),
                product.getCategories(),
                product.getTags(),
                product.getIsFreeShipping(),
                product.getStatus().getValue(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        )).orElseThrow(() -> {
            log.error("Product not found");
            return new ProductException("Product not found");
        });
    }

    @Transactional
    public void deleteProductById(String id) {
        AtomicReference<Product> productToDelete = new AtomicReference<>();
        productRepository.findById(id).ifPresentOrElse(
                productToDelete::set,
                () -> {
                    log.error("Product not found");
                    throw new ProductException("Product not found");
                }
        );
        this.changeStatus(productToDelete.get(), Status.DELETED);
        log.info("Product deleted: {}", productToDelete.get().getId());

        productDeletedProducer.sendEvent(
                ProductDeletedEvent.fromProduct(
                        productToDelete.get(),
                        microserviceProperties
                ));
        log.info("Product deleted event sent: {}", productToDelete.get());
    }

    @Transactional
    public void changeStatus(Product product, Status status) {
        Status from = product.getStatus();
        product.setStatus(status);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        log.info("Product status changed: {}", product);

        productChangedStatusProducer.sendEvent(
                ProductChangedStatusEvent.fromProduct(
                        product,
                        from,
                        status,
                        microserviceProperties
                ));
        log.info("Product changed status event sent: {}", product);

    }
}
