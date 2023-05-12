package pe.martinsam.catalogproducts.domain.model.product.event;

import jakarta.validation.constraints.NotNull;
import pe.martinsam.catalogproducts.common.infraestructure.property.MicroserviceProperties;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.event.payload.ProductDeletedPayload;
import pe.martinsam.catalogproducts.domain.model.product.event.types.ProductEventType;

import java.util.UUID;

public record ProductDeletedEvent(ProductEventType type,
                                  String eventId,
                                  String occurredOn,
                                  String version,
                                  String schema,
                                  String eventSource,
                                  ProductDeletedPayload payload
) implements IBaseProductEvent {
    public static ProductDeletedEvent fromProduct(
            @NotNull Product product,
            @NotNull MicroserviceProperties microserviceProperties
    ) {
        return new ProductDeletedEvent(
                ProductEventType.PRODUCT_DELETED,
                UUID.randomUUID().toString(),
                product.getUpdatedAt().toString(),
                microserviceProperties.getVersion(),
                microserviceProperties.getSchema(),
                microserviceProperties.getId(),
                new ProductDeletedPayload(
                        product.getId(),
                        product.getUpdatedAt())
        );
    }
}
