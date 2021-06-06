package sda.store.onlinestore.model;

import lombok.Data;
import sda.store.onlinestore.enums.ProductType;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

//@Valid
@Data
public class ProductDTO {

    private String title;

    private String description;

    private Double price;

    private ProductType productType;

    @OneToMany(mappedBy = "product")
    private List<ProductQuantity> productQuantities;
}
