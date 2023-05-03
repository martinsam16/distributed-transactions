package pe.martinsam.catalogproducts.domain.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.martinsam.catalogproducts.domain.model.product.types.Status;
import pe.martinsam.catalogproducts.infraestructure.persistence.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class Product {

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

    private List<Category> categories;
    private List<String> tags;

    private Boolean isFreeShipping;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    public ProductEntity toProductEntity() {
        return ProductEntity.builder()
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
                .categories(this.categories.stream().map(Category::getId).toList())
                .tags(this.tags)
                .isFreeShipping(this.isFreeShipping)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .status(this.status.getValue())
                .build();
    }

}
