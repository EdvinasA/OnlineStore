package sda.store.onlinestore.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Min(value = 0, message = "Quantity cannot be less than zero!")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
