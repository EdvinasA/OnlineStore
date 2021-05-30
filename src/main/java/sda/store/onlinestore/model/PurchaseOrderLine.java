package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //@Min(value = 0, message = "Quantity cannot be < 0")
    private Double quantity;
}
