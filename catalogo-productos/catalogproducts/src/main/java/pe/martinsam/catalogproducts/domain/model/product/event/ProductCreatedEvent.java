package pe.martinsam.catalogproducts.domain.model.product.event;

import pe.martinsam.catalogproducts.domain.model.product.Category;
import pe.martinsam.catalogproducts.domain.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductCreatedEvent(String id,
                                  String sellerId,
                                  String name,
                                  String brand,
                                  String model,
                                  String description,
                                  List<String> imageUrl,
                                  Map<String, String> additionalProperties,
                                  String currencySymbol,
                                  BigDecimal price,
                                  Integer stock,
                                  List<String> categories,
                                  List<String> tags,
                                  Boolean isFreeShipping,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt,
                                  String status) {
    public static ProductCreatedEvent fromProduct(Product entity){
        return new ProductCreatedEvent(
                entity.getId(),
                entity.getSellerId(),
                entity.getName(),
                entity.getBrand(),
                entity.getModel(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getAdditionalProperties(),
                entity.getCurrency().getCurrencyCode(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCategories().stream().map(Category::getId).toList(),
                entity.getTags(),
                entity.getIsFreeShipping(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getStatus().getValue()
        );
    }
}
