package pe.martinsam.catalogproducts.domain.model.product.event;

import jakarta.validation.constraints.NotNull;
import pe.martinsam.catalogproducts.common.infraestructure.property.MicroserviceProperties;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.model.product.event.payload.ProductChangedStatusPayload;
import pe.martinsam.catalogproducts.domain.model.product.event.types.ProductEventType;
import pe.martinsam.catalogproducts.domain.model.product.types.Status;

import java.util.UUID;

public record ProductChangedStatusEvent(ProductEventType type,
                                        String eventId,
                                        String occurredOn,
                                        String version,
                                        String schema,
                                        String eventSource,
                                        ProductChangedStatusPayload payload
) implements IBaseProductEvent {

    public static ProductChangedStatusEvent fromProduct(
            @NotNull Product product,
            @NotNull Status from,
            @NotNull Status to,
            @NotNull MicroserviceProperties microserviceProperties
    ) {
        return new ProductChangedStatusEvent(
                ProductEventType.PRODUCT_STATUS_CHANGED,
                UUID.randomUUID().toString(),
                product.getUpdatedAt().toString(),
                microserviceProperties.getVersion(),
                microserviceProperties.getSchema(),
                microserviceProperties.getId(),
                new ProductChangedStatusPayload(
                        from,
                        to,
                        product.getId(),
                        product.getUpdatedAt().toString()
                )
        );
    }
}
