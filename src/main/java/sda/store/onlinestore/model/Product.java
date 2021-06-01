package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sda.store.onlinestore.enums.ProductType;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Double price;

    private ProductType productType;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductQuantity> productQuantities;
}
