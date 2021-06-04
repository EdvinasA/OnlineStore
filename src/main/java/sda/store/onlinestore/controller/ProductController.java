package sda.store.onlinestore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "**")
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

    @GetMapping(value = "/get-all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "/product-title")
    public List<Product> getProductsByTitle(String title) {
        return productService.getProductsByTitle(title);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) //po DELETE grazins likusi sarasa
    @ResponseBody
    public List<Product>deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return productService.getAllProducts();
    }
}
