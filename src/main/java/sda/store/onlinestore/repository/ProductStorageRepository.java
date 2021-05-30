package sda.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.store.onlinestore.model.ProductStorage;

@Repository
public interface ProductStorageRepository extends JpaRepository<ProductStorage, Long> {

}
