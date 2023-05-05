package pe.martinsam.catalogproducts.domain.model.product.event;

import java.time.LocalDateTime;

public record ProductDeletedEvent(
        String id,
        LocalDateTime deletedAt
) {
}
