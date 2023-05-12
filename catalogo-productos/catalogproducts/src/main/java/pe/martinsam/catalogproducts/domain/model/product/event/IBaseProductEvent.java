package pe.martinsam.catalogproducts.domain.model.product.event;

import pe.martinsam.catalogproducts.domain.model.product.event.types.ProductEventType;

public interface IBaseProductEvent {
    ProductEventType type();
    String eventId();
    String occurredOn();
    String version();
    String schema();
    String eventSource();

    Object payload(); //TODO fix with generics
}
