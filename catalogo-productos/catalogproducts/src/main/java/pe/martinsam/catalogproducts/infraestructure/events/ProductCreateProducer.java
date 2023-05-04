package pe.martinsam.catalogproducts.infraestructure.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pe.martinsam.catalogproducts.domain.model.product.event.ProductCreatedEvent;

@Service
public class ProductCreateProducer {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final String prefix;

    public ProductCreateProducer(
            KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate,
            @Value("${spring.kafka.topic-prefix}") String transactionIdPrefix
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.prefix = transactionIdPrefix;
    }

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        kafkaTemplate.send(prefix + "product-created", event.id(), event);
    }
}

