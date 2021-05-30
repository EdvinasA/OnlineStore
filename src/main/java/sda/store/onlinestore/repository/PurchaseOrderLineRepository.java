package sda.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.store.onlinestore.model.PurchaseOrderLine;

@Repository
public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine, Long> {
}
