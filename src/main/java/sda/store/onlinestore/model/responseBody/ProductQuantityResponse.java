package sda.store.onlinestore.model.responseBody;

import lombok.Data;
import sda.store.onlinestore.enums.ProductType;
import sda.store.onlinestore.model.Product;

import java.time.LocalDate;

@Data
public class ProductQuantityResponse {
    private Product product;
    private String title;
    private String description;
    private Double price;
    private String type;
    //ProductType productType;
    private Double quantity;
    private LocalDate onDate;
}
