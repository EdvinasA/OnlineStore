package sda.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.store.onlinestore.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
