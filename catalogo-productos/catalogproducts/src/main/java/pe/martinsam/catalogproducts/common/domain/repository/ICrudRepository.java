package pe.martinsam.catalogproducts.common.domain.repository;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T, ID>{
    Optional<List<T>> findAll();
    Optional<T> findById(ID id);
    Boolean existsById(ID id);
    T save(T entity);
    void deleteById(ID id);
    Long count();

}
