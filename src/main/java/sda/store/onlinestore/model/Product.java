package sda.store.onlinestore.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String description;


    private Double price;


    private ProductType type;


   /* @OneToMany(mappedBy = "product")
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "product")
    private List<ProductStorage> productStorages; */
}
