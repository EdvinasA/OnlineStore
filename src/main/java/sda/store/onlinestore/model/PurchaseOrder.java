package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Max(value = 100, message = "User name should not be longer than 100")
    @Column(name = "user_name")
    private String userName;

    //@Max(value = 100, message = "User surname should not be longer than 100")
    @Column(name = "user_surname")
    private String userSurname;

    //@Max(value = 100, message = "Delivery address should not be longer than 100")
    @Column(name = "delivery_address")
    private String deliveryAddress;

    //@FutureOrPresent(message = "Order date should be today or the future")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private List<PurchaseOrderLine> purchaseOrderLines;

}
