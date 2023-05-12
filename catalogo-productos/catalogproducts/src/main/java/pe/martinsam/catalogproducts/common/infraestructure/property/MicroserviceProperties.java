package pe.martinsam.catalogproducts.common.infraestructure.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.application")
@Getter
@Setter
public class MicroserviceProperties {

    private String name;
    private String id;
    private String schema;
    private String description;
    private String version;
    private String author;

}
