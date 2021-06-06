package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.ProductQuantity;
import sda.store.onlinestore.model.ProductQuantityDTO;
import sda.store.onlinestore.model.responseBody.ProductQuantityResponse;
import sda.store.onlinestore.service.ProductQuantityService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/product/quantity")
public class ProductQuantityController {
    @Autowired
    private ProductQuantityService productQuantityService;

    @PostMapping
    public ProductQuantity postProductQuantity(@RequestBody ProductQuantityDTO productQuantityDTO){
        return productQuantityService.postProductQuantity(productQuantityDTO);
    }

    @GetMapping
    public List<ProductQuantity> getAllProductQuantity(){
        return productQuantityService.getAllProductQuantity();
    }

    @GetMapping(value = "/{id}")
    public ProductQuantity getProductQuantityById(@RequestParam Long productQuantityId){
        return productQuantityService.getProductQuantityById(productQuantityId);
    }

/*    @GetMapping(value = "/onDate")
    public List<ProductQuantityResponse> getAllProductQuantityOnDate(@RequestParam("date")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return productQuantityService.getAllProductQuantityOnDate(date);
    }*/

/*    @GetMapping(value = "/onDate/id")
    public Double getQuantityByProductIdOnDate(@RequestParam("date")
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         @RequestParam("productId") Long productId){
        return productQuantityService.getQuantityByProductIdOnDate(date, productId);
    }*/

}
