package sda.store.onlinestore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.service.ProductService;
import main.java.sda.store.onlinestore.model.Product;

@RequestMapping(value = "/products")
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

}
