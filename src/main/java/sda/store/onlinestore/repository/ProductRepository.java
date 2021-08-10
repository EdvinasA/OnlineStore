package sda.store.onlinestore.repository;

import sda.store.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByTitleIgnoreCase(String title);

    void deleteById(Long id);


    Product getById(Long id);
}
