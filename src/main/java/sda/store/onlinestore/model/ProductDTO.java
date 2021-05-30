package sda.store.onlinestore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ProductDTO {

    private String title;

    private String description;

    private Double price;

    private ProductType productType;

    @OneToMany(mappedBy = "product")
    private List<ProductQuantity> productQuantities;
}
