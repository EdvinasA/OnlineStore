package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;
import sda.store.onlinestore.enums.ProductType;

import javax.persistence.OneToMany;
import java.util.List;

//@Valid
@ToString
@Data
public class ProductDTO {

    private String title;

    private String description;

    private Double price;

    private String type;
    //ProductType productType;

    @OneToMany(mappedBy = "product")
    private List<ProductQuantity> productQuantities;
}
