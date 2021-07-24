package sda.store.onlinestore.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //@Min(value = 0, message = "Quantity cannot be < 0")
    private Double quantity;








}
