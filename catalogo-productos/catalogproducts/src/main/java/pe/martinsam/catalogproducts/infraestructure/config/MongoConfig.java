package pe.martinsam.catalogproducts.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoRepositories(basePackages = "pe.martinsam.catalogproducts.infraestructure.persistence")
@EnableTransactionManagement
public class MongoConfig  {}

