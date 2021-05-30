package sda.store.onlinestore.model;

import lombok.Data;


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
    private List<ProductStorage> productStorages;

    @OneToMany(mappedBy = "product")
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "product")
    private List<ProductQuantity> productQuantities;
}
