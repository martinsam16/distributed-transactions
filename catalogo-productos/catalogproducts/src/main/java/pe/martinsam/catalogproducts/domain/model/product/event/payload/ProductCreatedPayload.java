package pe.martinsam.catalogproducts.domain.model.product.event.payload;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductCreatedPayload(String id,
                                    String sellerId,
                                    String name,
                                    String brand,
                                    String model,
                                    String description,
                                    List<String> imageUrl,
                                    Map<String, String> additionalProperties,
                                    String currencySymbol,
                                    BigDecimal price,
                                    Integer stock,
                                    List<String> categories,
                                    List<String> tags,
                                    Boolean isFreeShipping,
                                    LocalDateTime createdAt,
                                    LocalDateTime updatedAt,
                                    String status) {
}
