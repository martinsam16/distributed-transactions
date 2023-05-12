package pe.martinsam.catalogproducts.infraestructure.persistence.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pe.martinsam.catalogproducts.infraestructure.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends MongoRepository<ProductEntity, String> {

    @Query("{'status': {$ne: 'deleted'}}")
    List<ProductEntity> findAll();

    @Query(value = "{'id': ?0, 'status': {$ne: 'deleted'}}")
    Optional<ProductEntity> findById(String id);

    @Query(value = "{'name': ?0, sellerId: ?1, 'status': {$ne: 'deleted'}}")
    Optional<ProductEntity> findByNameAndSellerId(String name, String sellerId);

    @Query(value = "{sellerId: ?0, 'status': {$eq: 'published'}}")
    Long countBySellerId(String sellerId);

}
