package pe.martinsam.catalogproducts.domain.model.product.dto;


import pe.martinsam.catalogproducts.domain.model.product.Category;
import pe.martinsam.catalogproducts.domain.model.product.types.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public record GetProductDto(
        String id,
        String sellerId,

        String name,
        String brand,
        String model,
        String description,
        List<String> imageUrl,
        Map<String, String> additionalProperties,

        Currency currency,
        BigDecimal price,
        Integer stock,

        List<Category> categories,
        List<String> tags,

        Boolean isFreeShipping,

        String status,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public String getCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getUpdatedAt() {
        return updatedAt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}