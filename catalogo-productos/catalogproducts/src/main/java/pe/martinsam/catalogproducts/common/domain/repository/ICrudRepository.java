package pe.martinsam.catalogproducts.common.domain.repository;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T, ID>{
    Optional<List<T>> findAll();
    Optional<T> findById(ID id);
    Optional<T> findByNameAndSellerId(String name, String sellerId);
    Boolean existsById(ID id);
    T save(T entity);
    void deleteById(ID id);
    Long count();
    Long countBySellerId(String sellerId);
}
