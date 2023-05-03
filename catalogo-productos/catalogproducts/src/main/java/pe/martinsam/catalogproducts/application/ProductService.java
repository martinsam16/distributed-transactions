package pe.martinsam.catalogproducts.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.martinsam.catalogproducts.application.exception.ProductException;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.dto.CreateProductDto;
import pe.martinsam.catalogproducts.domain.model.product.dto.GetProductDto;
import pe.martinsam.catalogproducts.domain.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(CreateProductDto productDto) {
        productRepository.findByNameAndSellerId(productDto.name(), productDto.sellerId())
                .ifPresentOrElse(
                        p -> {
                            log.error("Product already exists");
                            throw new ProductException("Product already exists");
                        },
                        () -> log.info("Product not exists"));

        return productRepository.save(productDto.toProduct());
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
