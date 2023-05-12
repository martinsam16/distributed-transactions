package pe.martinsam.catalogproducts.domain.repository;

import pe.martinsam.catalogproducts.common.domain.repository.ICrudRepository;
import pe.martinsam.catalogproducts.domain.model.product.Product;

import java.util.Optional;

public interface ProductRepository extends ICrudRepository<Product, String> {

    Optional<Product> findByNameAndSellerId(String name, String sellerId);
    Long countBySellerId(String sellerId);

    void increaseStock(String id, Integer quantity);
    void decreaseStock(String id, Integer quantity);
}
