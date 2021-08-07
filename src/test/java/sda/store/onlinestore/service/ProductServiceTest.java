package sda.store.onlinestore.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import sda.store.onlinestore.OnlineStoreApplicationTests;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.repository.ProductQuantityRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest extends OnlineStoreApplicationTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductQuantityRepository productQuantityRepository;

    @InjectMocks
    private ProductService productService;

     @Test
     void whenRegisteredNewProduct() {
         //given
         ProductDTO product = new ProductDTO();
         product.setTitle("product");

         Product expectedProduct = new Product();
         expectedProduct.setTitle("product");
         expectedProduct.setImageUrl("assets/images/null");

         //when
         productService.newProductRegistration(product);

         //then
         ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

         verify(productRepository).save(productArgumentCaptor.capture());

         Product capturedProduct = productArgumentCaptor.getValue();

         assertThat(capturedProduct).isEqualTo(expectedProduct);
     }


    @Test
    void canGetAllProducts() {
        // when
        productService.getAllProducts();
        //then
        verify(productRepository).findAll();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);

        when(this.productRepository.findProductById(1L)).thenReturn(java.util.Optional.of(product));
        Product product1 = productService.getProductById(1L);

        assertThat(product1).isEqualTo(product);
    }


    @Test
    void getProductById_Throws_NotFoundException() {
        //given
        Long id = 2L;
        //when
        // then
        assertThatThrownBy(() -> productService.getProductById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Product id = %d not found", id);
    }


    @Test
    void return_empty_when_ProductList_Empty() {
        List<Product> expected = new ArrayList<>();
        when(this.productRepository.findAll()).thenReturn(expected);
        List<Product> product1 = productService.getAllProducts();

        assertThat(product1).isEqualTo(expected);
    }

    @Test
    void WhenValidTitle_getProductsByTitleShouldBeFound() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Computer");
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        when(this.productRepository.findProductsByTitleIgnoreCase("Computer")).thenReturn(expectedProducts);
        List<Product> product1 = productService.getProductsByTitle("Computer");

        assertThat(product1).isEqualTo(expectedProducts);
    }

    @Test
    @Disabled
    void updateProductById() {

    }

    @Test
    @Disabled
    void getAllProductQuantityOnDate() {
    }

    @Test
    @Disabled
    void getQuantityByProductIdOnDate() {
    }

}
