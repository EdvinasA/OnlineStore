package sda.store.onlinestore.model;

import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
public class ProductStorageDTO {
    private Long productId;
    private Double quantity;
    private LocalDate date;
}