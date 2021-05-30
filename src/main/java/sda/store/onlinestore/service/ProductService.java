package sda.store.onlinestore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product newProductRegistration(ProductDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setType(productDTO.getType());
        productRepository.save(product);
        return product;
    }
}
