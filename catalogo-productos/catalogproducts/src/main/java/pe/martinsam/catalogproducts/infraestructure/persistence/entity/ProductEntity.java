package pe.martinsam.catalogproducts.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.martinsam.catalogproducts.domain.model.product.Category;
import pe.martinsam.catalogproducts.domain.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import pe.martinsam.catalogproducts.domain.model.product.types.Status;

@Document(collection = "product")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    private String id;

    private String sellerId;

    private String name;
    private String brand;
    private String model;
    private String description;
    private List<String> imageUrl;
    private Map<String, String> additionalProperties;

    private Currency currency;
    private BigDecimal price;
    private Integer stock;

    private List<String> categories;
    private List<String> tags;

    private Boolean isFreeShipping;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    public Product toProduct(){
        return Product.builder()
                .id(this.id)
                .sellerId(this.sellerId)
                .name(this.name)
                .brand(this.brand)
                .model(this.model)
                .description(this.description)
                .imageUrl(this.imageUrl)
                .additionalProperties(this.additionalProperties)
                .currency(this.currency)
                .price(this.price)
                .stock(this.stock)
                .categories(this.categories.stream().map(Category::fromString).toList())
                .tags(this.tags)
                .isFreeShipping(this.isFreeShipping)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .status(Status.fromValue(this.status))
                .build();
    }
}
