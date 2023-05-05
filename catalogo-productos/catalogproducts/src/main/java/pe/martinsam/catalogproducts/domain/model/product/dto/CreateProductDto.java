package pe.martinsam.catalogproducts.domain.model.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import pe.martinsam.catalogproducts.domain.model.product.Category;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.types.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public record CreateProductDto(

        @NotBlank(message = "El campo 'sellerId' es obligatorio")
        String sellerId,

        @NotBlank(message = "El campo 'name' es obligatorio")
        String name,
        @NotBlank(message = "El campo 'brand' es obligatorio")
        String brand,
        String model,
        @NotBlank(message = "El campo 'description' es obligatorio")
        String description,
        @Size(min = 1, message = "Se debe proporcionar al menos una imagen")
        @NotEmpty(message = "El campo 'imageUrl' es obligatorio")
        List<@URL(message = "El campo 'imageUrl' debe ser una URL válida") String> imageUrl,
        Map<String, String> additionalProperties,

        @NotBlank(message = "El campo 'currency' es obligatorio")
        String currencySymbol,
        @NotNull(message = "El campo 'price' es obligatorio")
        @PositiveOrZero(message = "El precio no puede ser negativo")
        BigDecimal price,
        @NotNull(message = "El campo 'stock' es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock,

        @Size(min = 1, message = "Se debe proporcionar al menos una categoría")
        @NotEmpty(message = "El campo 'categories' es obligatorio")
        List<Category> categories,
        List<String> tags,

        Boolean isFreeShipping
) {
    public Product toProduct() {
        return Product.builder()
                .sellerId(this.sellerId)
                .name(this.name)
                .brand(this.brand)
                .model(this.model)
                .description(this.description)
                .imageUrl(this.imageUrl)
                .additionalProperties(this.additionalProperties)
                .currency(Currency.getInstance(this.currencySymbol))
                .price(this.price)
                .stock(this.stock)
                .categories(this.categories)
                .tags(this.tags)
                .isFreeShipping(this.isFreeShipping)
                .status(Status.DRAFT)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
