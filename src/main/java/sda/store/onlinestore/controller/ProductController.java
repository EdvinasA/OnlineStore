package sda.store.onlinestore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.service.ProductService;
import sda.store.onlinestore.model.Product;

import java.util.List;

@RequestMapping(value = "/products")
@CrossOrigin(value = "http://localhost:4200/", allowedHeaders = "*")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product newProductRegistration(@RequestBody ProductDTO productDTO) {
        return productService.newProductRegistration(productDTO);
    }

    @GetMapping(value = "/get-all/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
