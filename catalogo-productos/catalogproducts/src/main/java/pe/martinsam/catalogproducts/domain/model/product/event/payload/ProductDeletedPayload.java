package pe.martinsam.catalogproducts.domain.model.product.event.payload;

import java.time.LocalDateTime;

public record ProductDeletedPayload(
        String id,
        LocalDateTime deletedAt
) {
}
