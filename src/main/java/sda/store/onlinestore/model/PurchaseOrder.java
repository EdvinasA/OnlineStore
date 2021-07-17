package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, message = "User name should not be longer than 100")
    private String userName;

    @Size(max = 100, message = "User surname should not be longer than 100")
    private String userSurname;

    @Size(max = 100, message = "Delivery address should not be longer than 100")
    private String deliveryAddress;

    //@FutureOrPresent(message = "Order date should be today or the future")
    private LocalDate orderDate;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private List<PurchaseOrderLine> purchaseOrderLines;

    @ManyToOne
    private User user;

}
