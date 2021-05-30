package sda.store.onlinestore.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class ProductDTO {

    private String title;

    private String description;

    private Double price;

    private Double quantity;

      private String type;
}
