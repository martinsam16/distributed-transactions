package pe.martinsam.catalogproducts.domain.model.product.event.payload;

import pe.martinsam.catalogproducts.domain.model.product.types.Status;

public record ProductChangedStatusPayload(
        Status from,
        Status to,
        String productId,
        String updatedAt
) {
}
