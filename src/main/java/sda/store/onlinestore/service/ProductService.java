package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import org.springframework.util.Assert;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

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
        product.setProductType(productDTO.getProductType());
        product.setProductQuantities(productDTO.getProductQuantities());
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product id = %d not found", id)));
    }

    public List<Product> getProductsByTitle(String title) {
        return productRepository.findProductsByTitle(title);
        // .orElseThrow(() -> new NotFoundException(String.format("Product title = %d not found", title)));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public Product updateProductById(Long id, ProductDTO productDTO) {
        Product productToUpdate = productRepository.getOne(id);
        productToUpdate.setTitle(productDTO.getTitle());
        productToUpdate.setDescription(productDTO.getDescription());
        productToUpdate.setPrice(productDTO.getPrice());
        productToUpdate.setProductType(productDTO.getProductType());
        productToUpdate.setProductQuantities(productDTO.getProductQuantities());
        productRepository.save(productToUpdate);
        return productToUpdate;
    }
}
