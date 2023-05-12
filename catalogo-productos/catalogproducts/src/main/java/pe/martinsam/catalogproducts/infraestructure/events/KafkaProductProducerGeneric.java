package pe.martinsam.catalogproducts.infraestructure.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.martinsam.catalogproducts.domain.model.product.event.IBaseProductEvent;

@Service
public class KafkaProductProducerGeneric<T extends IBaseProductEvent> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final String topic;

    public KafkaProductProducerGeneric(
            KafkaTemplate<String, T> kafkaTemplate,
            @Value("${spring.kafka.custom.product.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendEvent(T event) {
        kafkaTemplate.send(topic, event.eventId(), event);
    }
}
