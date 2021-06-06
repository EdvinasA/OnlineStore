package sda.store.onlinestore.service;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.enums.ProductType;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductQuantity;
import sda.store.onlinestore.model.ProductQuantityDTO;
import sda.store.onlinestore.model.responseBody.ProductQuantityResponse;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.ProductQuantityRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
        productQuantityRepository.save(productQuantity);
        return productQuantity;
    }

    public List<ProductQuantity> getAllProductQuantity() {
        return productQuantityRepository.findAll();
    }

    public ProductQuantity getProductQuantityById(Long productQuantityId) {
        Optional<ProductQuantity> productQuantityOpt = productQuantityRepository.findById(productQuantityId);
        return productQuantityOpt.orElseThrow(() -> new RuntimeException("Product quantity was not found"));
    }

    /*public List<ProductQuantityResponse> getAllProductQuantityOnDate(LocalDate date) {
        List<ProductQuantityResponse> productQuantityResponses = new ArrayList<>();
        List<Object[]> productAndQuantityObj = productQuantityRepository.findAllProductQuantities(date);
        for (Object[] o: productAndQuantityObj) {
            ProductQuantityResponse productQuantityResponse = new ProductQuantityResponse();

            Optional<Product> productOpt = productRepository.findById((Long) o[0]);
            Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
            productQuantityResponse.setProduct(product);

            productQuantityResponse.setQuantity((Double) o[1]);
            productQuantityResponse.setOnDate(date);
            productQuantityResponses.add(productQuantityResponse);
        }
        return productQuantityResponses;
    }*/

    public List<ProductQuantityResponse> getAllProductQuantityOnDate(LocalDate date) {
        List<ProductQuantityResponse> productQuantityResponses = new ArrayList<>();
        List<Object[]> productAndQuantityObj = productQuantityRepository.findAllProductQuantities(date);
        for (Object[] o: productAndQuantityObj) {
            ProductQuantityResponse productQuantityResponse = new ProductQuantityResponse();

            Optional<Product> productOpt = productRepository.findById((Long) o[0]);
            Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));

            productQuantityResponse.setProduct(product);
            productQuantityResponse.setTitle(product.getTitle());
            productQuantityResponse.setDescription(product.getDescription());
            productQuantityResponse.setPrice(product.getPrice());
            productQuantityResponse.setType(product.getType());
            productQuantityResponse.setQuantity((Double) o[1]);
            productQuantityResponse.setOnDate(date);
            productQuantityResponses.add(productQuantityResponse);
        }
        return productQuantityResponses;
    }

    public Double etQuantityByProductIdOnDate(LocalDate date, Long productId) {
        return productQuantityRepository.findProductQuantityById(date, productId);
    }
}
