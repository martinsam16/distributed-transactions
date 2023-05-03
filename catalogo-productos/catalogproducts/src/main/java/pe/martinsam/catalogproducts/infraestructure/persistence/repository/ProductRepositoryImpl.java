package pe.martinsam.catalogproducts.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.martinsam.catalogproducts.domain.model.product.Product;
import pe.martinsam.catalogproducts.domain.repository.ProductRepository;
import pe.martinsam.catalogproducts.infraestructure.persistence.dao.ProductDao;
import pe.martinsam.catalogproducts.infraestructure.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDao productDao;

    @Override
    public Optional<List<Product>> findAll() {
        return Optional.of(productDao.findAll().stream().map(ProductEntity::toProduct).toList());
    }

    @Override
    public Optional<Product> findById(String id) {
        return productDao.findById(id).map(ProductEntity::toProduct);
    }

    @Override
    public Optional<Product> findByNameAndSellerId(String name, String sellerId) {
        return productDao.findByNameAndSellerId(name, sellerId).map(ProductEntity::toProduct);
    }

    @Override
    public Boolean existsById(String id) {
        return productDao.existsById(id);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product.toProductEntity()).toProduct();
    }

    @Override
    public void deleteById(String id) {
        productDao.deleteById(id);
    }

    @Override
    public Long count() {
        return productDao.count();
    }
}
