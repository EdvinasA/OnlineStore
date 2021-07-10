package sda.store.onlinestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "product_quantity")
public class ProductQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //@Min(value = 0, message = "Quantity cannot be < 0")
    private Double quantity;

    //@FutureOrPresent(message = "Quantity date should be today or the future")
    private LocalDate date;
}
