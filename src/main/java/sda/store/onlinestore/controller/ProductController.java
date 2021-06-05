package sda.store.onlinestore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.service.ProductService;

import java.util.List;

@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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

    @GetMapping(value = "/title")
    public List<Product> getProductsByTitle(String title) {
        return productService.getProductsByTitle(title);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

  /*  @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.updateProductById(id, productDTO);
        return ResponseEntity.ok(productDTO);
    } */

   @PutMapping("/{id}")
    ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        if (productService.getProductById(id) != null)
            return new ResponseEntity(productService.updateProductById(id, productDTO), HttpStatus.OK);
        else
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
