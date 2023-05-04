package pe.martinsam.catalogproducts.infraestructure.persistence.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.martinsam.catalogproducts.infraestructure.persistence.entity.ProductEntity;

import java.util.Optional;
@Repository
public interface ProductDao extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findById(String id);

    Optional<ProductEntity> findByNameAndSellerId(String name, String sellerId);

    Long countBySellerId(String sellerId);

}
