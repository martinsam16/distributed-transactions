package pe.martinsam.catalogproducts.domain.repository;

import pe.martinsam.catalogproducts.common.repository.CrudRepository;
import pe.martinsam.catalogproducts.domain.model.product.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
