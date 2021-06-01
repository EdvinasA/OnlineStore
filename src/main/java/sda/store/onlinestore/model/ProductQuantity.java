package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "product_quantity")
public class ProductQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //@Min(value = 0, message = "Quantity cannot be < 0")
    private Double quantity;

    //@FutureOrPresent(message = "Quantity date should be today or the future")
    private LocalDate date;
}
