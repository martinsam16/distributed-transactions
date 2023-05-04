package pe.martinsam.catalogproducts.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ClusterAuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.martinsam.catalogproducts.application.exception.ProductException;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.dto.CreateProductDto;
import pe.martinsam.catalogproducts.domain.model.product.dto.GetProductDto;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductCreatedEvent;
import pe.martinsam.catalogproducts.domain.repository.ProductRepository;
import pe.martinsam.catalogproducts.infraestructure.events.ProductCreateProducer;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCreateProducer productCreateProducer;

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
        try {
            productCreateProducer.sendProductCreatedEvent(ProductCreatedEvent.fromProduct(savedProduct));
        } catch (Exception e) {
            log.error("Error publishing event to Kafka: {}", e.getMessage());
            productRepository.deleteById(savedProduct.getId());
            throw e;
        }
        return savedProduct;
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
}
