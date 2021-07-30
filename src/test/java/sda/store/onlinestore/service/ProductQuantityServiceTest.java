package sda.store.onlinestore.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import sda.store.onlinestore.OnlineStoreApplicationTests;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.model.ProductQuantity;
import sda.store.onlinestore.model.ProductQuantityDTO;
import sda.store.onlinestore.repository.ProductQuantityRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class ProductQuantityServiceTest extends OnlineStoreApplicationTests {
//
//    @InjectMocks
//    private ProductQuantityService productQuantityService;
//    private ProductService productService;
//
//    @Mock
//    private ProductQuantityRepository productQuantityRepository;
//    private ProductRepository productRepository;
//
//    @Test
//    void postProductQuantity_whenCreatedFindByFirstID() {
//        Product product = new Product();
//        product.setTitle("Computer");
//        productRepository.save(product);
//        ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO();
//        productQuantityDTO.setProductId(1L);
//        productQuantityDTO.setQuantity(20.00);
//        productQuantityDTO.setDate(LocalDate.of(2021, 6, 12));
//        ProductQuantity productQuantity = productQuantityService.postProductQuantity(productQuantityDTO);
//
//        Mockito.when(this.productRepository.findProductById(1L)).thenReturn(Optional.of(product));
//        Mockito.when(this.productQuantityRepository.findById(1L)).thenReturn(Optional.ofNullable(productQuantity));
//        ProductQuantity foundProductQuantity = productQuantityService.getProductQuantityById(1L);
//
//        assertThat(foundProductQuantity).isEqualTo(productQuantity);
//    }
//
//    @Test
//    void getAllProductQuantity() {
//    }
//
//    @Test
//    void getProductQuantityById() {
//    }
//
//    @Test
//    void getQuantityByProductIdOnDate() {
//    }
}
