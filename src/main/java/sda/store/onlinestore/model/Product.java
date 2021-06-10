package sda.store.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import sda.store.onlinestore.enums.ProductType;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@ToString
@Entity
@Data
//@Valid
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    //@NotNull
    private String title;

    //@NotNull
    private String description;

    //@NotNull
  //  @Positive
    private Double price;

    private String type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductQuantity> productQuantities;
}
