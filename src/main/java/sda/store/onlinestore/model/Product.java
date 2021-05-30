package main.java.sda.store.onlinestore.model;

import lombok.Data;


import javax.persistence.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String description;


    private Double price;


    private Double quantity;


    private String type;


   /* @OneToMany(mappedBy = "product")
    private List<PurchaseOrderLine> purchaseOrderLines;

    @OneToMany(mappedBy = "product")
    private List<ProductStorage> productStorages; */
}
