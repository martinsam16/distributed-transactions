package pe.martinsam.catalogproducts.infraestructure.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductDeletedEvent;

@Service
public class ProductDeletedProducer {

    private final KafkaTemplate<String, ProductDeletedEvent> kafkaTemplate;
    private final String topic;

    public ProductDeletedProducer(
            KafkaTemplate<String, ProductDeletedEvent> kafkaTemplate,
            @Value("${spring.kafka.custom.product.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendProductDeletedEvent(ProductDeletedEvent event) {
        kafkaTemplate.send(topic, event.id(), event);
    }
}

