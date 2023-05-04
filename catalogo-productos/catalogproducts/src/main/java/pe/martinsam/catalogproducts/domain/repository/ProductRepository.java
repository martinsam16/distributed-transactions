package pe.martinsam.catalogproducts.domain.repository;

import pe.martinsam.catalogproducts.common.repository.ICrudRepository;
import pe.martinsam.catalogproducts.domain.model.product.Product;

public interface ProductRepository extends ICrudRepository<Product, String> {
}
