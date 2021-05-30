package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductStorage;
import sda.store.onlinestore.model.ProductStorageDTO;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.ProductStorageRepository;

import java.util.Optional;

@Service
public class ProductStorageService {
    @Autowired
    private ProductStorageRepository productStorageRepository;

    @Autowired
    private ProductRepository productRepository;


    public ProductStorage postProductStorage(ProductStorageDTO productStorageDTO) {
        ProductStorage productStorage = new ProductStorage();
        Optional<Product> productOpt = productRepository.findById(productStorageDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
        productStorage.setProduct(product);
        productStorage.setQuantity(productStorageDTO.getQuantity());
        productStorage.setDate(productStorageDTO.getDate());
        productStorageRepository.save(productStorage);
        return productStorage;
    }
}
