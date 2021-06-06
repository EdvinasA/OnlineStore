package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.responseBody.ProductQuantityResponse;
import sda.store.onlinestore.service.ProductService;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product newProductRegistration(@Valid @RequestBody ProductDTO productDTO) {
        return productService.newProductRegistration(productDTO);
    }

    @GetMapping(value = "/get-all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

   /* @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    } */

    @GetMapping(value = "/get-all/quantity")
    public List<ProductQuantityResponse> getAllProductQuantityOnDate(@RequestParam("date")
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return productService.getAllProductQuantityOnDate(date);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (productService.getProductById(id) != null) {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }
        else {
           // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new NotFoundException("Product not found, productId: " + id);
        }
    }

    @GetMapping(value = "/title")
    public ResponseEntity<List<Product>> getProductsByTitle(String title) {
            return new ResponseEntity<>(productService.getProductsByTitle(title), HttpStatus.OK);
     }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        if (productService.getProductById(id) == null) {
            throw new NotFoundException("Product not found, productId: " + id);
        } else {
            productService.deleteProductById(id);
            return ResponseEntity.ok().build();
        }
    }

   @PutMapping("/{id}")
    ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        if (productService.getProductById(id) != null)
            return new ResponseEntity(productService.updateProductById(id, productDTO), HttpStatus.OK);
        else
           return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
