package sda.store.onlinestore.model.responseBody;

import lombok.Data;
import sda.store.onlinestore.model.Product;

import java.time.LocalDate;

@Data
public class ProductQuantityResponse {
    private Product product;
    private Double quantity;
    private LocalDate onDate;
}
