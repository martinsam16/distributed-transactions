package pe.martinsam.catalogproducts.common.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <T, ID>{
    Optional<List<T>> findAll();
    Optional<T> findById(ID id);
    Optional<T> findByNameAndSellerId(String name, String sellerId);
    Boolean existsById(ID id);
    T save(T entity);
    void deleteById(ID id);
    Long count();
}
