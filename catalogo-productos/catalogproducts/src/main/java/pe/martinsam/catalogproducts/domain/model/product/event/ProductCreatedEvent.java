package pe.martinsam.catalogproducts.domain.model.product.event;

import jakarta.validation.constraints.NotNull;
import pe.martinsam.catalogproducts.common.infraestructure.property.MicroserviceProperties;
import pe.martinsam.catalogproducts.domain.model.product.Category;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.event.payload.ProductCreatedPayload;
import pe.martinsam.catalogproducts.domain.model.product.event.types.ProductEventType;

import java.util.UUID;

public record ProductCreatedEvent(ProductEventType type,
                                  String eventId,
                                  String occurredOn,
                                  String version,
                                  String schema,
                                  String eventSource,
                                  ProductCreatedPayload payload
) implements IBaseProductEvent {

    public static ProductCreatedEvent fromProduct(
            @NotNull Product product,
            @NotNull MicroserviceProperties microserviceProperties
    ) {
        return new ProductCreatedEvent(
                ProductEventType.PRODUCT_CREATED,
                UUID.randomUUID().toString(),
                product.getCreatedAt().toString(),
                microserviceProperties.getVersion(),
                microserviceProperties.getSchema(),
                microserviceProperties.getId(),
                new ProductCreatedPayload(
                        product.getId(),
                        product.getSellerId(),
                        product.getName(),
                        product.getBrand(),
                        product.getModel(),
                        product.getDescription(),
                        product.getImageUrl(),
                        product.getAdditionalProperties(),
                        product.getCurrency().getCurrencyCode(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategories().stream().map(Category::getId).toList(),
                        product.getTags(),
                        product.getIsFreeShipping(),
                        product.getCreatedAt(),
                        product.getUpdatedAt(),
                        product.getStatus().getValue()
                ));
    }
}
