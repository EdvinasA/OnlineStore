package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import org.springframework.util.Assert;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.model.ProductQuantity;
import sda.store.onlinestore.model.responseBody.ProductQuantityResponse;
import sda.store.onlinestore.repository.ProductQuantityRepository;
import sda.store.onlinestore.repository.ProductRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQuantityRepository productQuantityRepository;

    public Product newProductRegistration(ProductDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setImageUrl("assets/images/" + productDTO.getImageUrl());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setType(productDTO.getType());
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
        return productRepository.findProductsByTitleIgnoreCase(title);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProductById(Long id, ProductDTO productDTO) {
        Product productToUpdate = productRepository.getById(id);
        productToUpdate.setTitle(productDTO.getTitle());
        productToUpdate.setImageUrl(productDTO.getImageUrl());
        productToUpdate.setDescription(productDTO.getDescription());
        productToUpdate.setPrice(productDTO.getPrice());
        productToUpdate.setType(productDTO.getType());
        productToUpdate.setProductQuantities(productDTO.getProductQuantities());
        productRepository.save(productToUpdate);
        return productToUpdate;
    }

    public List<ProductQuantityResponse> getAllProductQuantityOnDate(LocalDate date) {
        List<Product> products = productRepository.findAll();
        List<ProductQuantityResponse> productQuantityResponses = new ArrayList<>();
        for (Product product: products) {
            ProductQuantityResponse productQuantityResponse = new ProductQuantityResponse();
            productQuantityResponse.setProduct(product);

            Double productQuantity = productQuantityRepository.findProductQuantityById(date, product.getId());
            productQuantityResponse.setQuantity(productQuantity == null ?0 : productQuantity);
            productQuantityResponse.setOnDate(date);
            productQuantityResponses.add(productQuantityResponse);
        }
        return productQuantityResponses;
    }

    public Double getQuantityByProductIdOnDate(LocalDate date, Long productId) {
        return productQuantityRepository.findProductQuantityById(date, productId);
    }

}
