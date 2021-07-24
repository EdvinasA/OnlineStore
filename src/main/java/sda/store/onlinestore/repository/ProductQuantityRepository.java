package sda.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductQuantity;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Long> {

    @Query("SELECT ps.product.id, sum(ps.quantity) from ProductQuantity as ps where ps.date<= :date GROUP BY ps.product")
    List<Object[]> findAllProductQuantities(LocalDate date);

    @Query("SELECT sum(ps.quantity) from ProductQuantity as ps where ps.date<= :date and ps.product.id = :productId")
    Double findProductQuantityById(LocalDate date, Long productId);

}
