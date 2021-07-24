package sda.store.onlinestore.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductQuantityDTO {
    private Long productId;
    private Double quantity;
    private LocalDate date;
}
