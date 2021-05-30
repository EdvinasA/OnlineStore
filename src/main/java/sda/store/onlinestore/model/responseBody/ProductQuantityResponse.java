package sda.store.onlinestore.model.responseBody;

import lombok.Data;
import sda.store.onlinestore.enums.ProductType;

import java.time.LocalDate;

@Data
public class ProductQuantityResponse {
    private String title;
    private String description;
    private Double price;
    private ProductType productType;
    private Double quantity;
    private LocalDate onDate;
}
