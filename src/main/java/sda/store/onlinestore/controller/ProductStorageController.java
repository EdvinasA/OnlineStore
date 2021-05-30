package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.store.onlinestore.model.ProductStorage;
import sda.store.onlinestore.model.ProductStorageDTO;
import sda.store.onlinestore.repository.ProductStorageRepository;
import sda.store.onlinestore.service.ProductStorageService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product/storage")
public class ProductStorageController {
    @Autowired
    private ProductStorageService productStorageService;

    @PostMapping
    public ProductStorage postProductStorage(@RequestBody ProductStorageDTO productStorageDTO){
        return productStorageService.postProductStorage(productStorageDTO);
    }


}
