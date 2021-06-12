package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductQuantity;
import sda.store.onlinestore.model.ProductQuantityDTO;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.ProductQuantityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQuantityService {
    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductQuantity postProductQuantity(ProductQuantityDTO productQuantityDTO) {
        ProductQuantity productQuantity = new ProductQuantity();
        Optional<Product> productOpt = productRepository.findById(productQuantityDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
        productQuantity.setProduct(product);
        productQuantity.setQuantity(productQuantityDTO.getQuantity());
        productQuantity.setDate(productQuantityDTO.getDate());
        return productQuantityRepository.save(productQuantity);
    }

    public List<ProductQuantity> getAllProductQuantity() {
        return productQuantityRepository.findAll();
    }

    public ProductQuantity getProductQuantityById(Long productQuantityId) {
        Optional<ProductQuantity> productQuantityOpt = productQuantityRepository.findById(productQuantityId);
        return productQuantityOpt.orElseThrow(() -> new RuntimeException("Product quantity was not found"));
    }
}
