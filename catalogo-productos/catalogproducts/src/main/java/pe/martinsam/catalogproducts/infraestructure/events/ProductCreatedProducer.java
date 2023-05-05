package pe.martinsam.catalogproducts.infraestructure.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductCreatedEvent;

@Service
public class ProductCreatedProducer {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final String topic;

    public ProductCreatedProducer(
            KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate,
            @Value("${spring.kafka.custom.product.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        kafkaTemplate.send(topic, event.id(), event);
    }
}

