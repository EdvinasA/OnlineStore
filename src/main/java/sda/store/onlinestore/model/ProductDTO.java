package sda.store.onlinestore.model;

import lombok.Data;

@Data
public class ProductDTO {

    private String title;

    private String description;

    private Double price;

    private ProductType type;
}
