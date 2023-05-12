package pe.martinsam.catalogproducts.domain.model.product.event;

public interface IBaseProductEvent {
    String eventId();
    String occurredOn();
    String version();
    String schema();
    String eventSource();
}
