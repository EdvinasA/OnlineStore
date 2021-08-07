package sda.store.onlinestore.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.ProductDTO;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

     @Test
     void whenRegisteredNewProduct() {
         //given
         ProductDTO product = new ProductDTO();
         product.setDescription("Large disc");
         product.setPrice(50.99);
         product.setTitle("Disc");
         product.setType("Computer");
         product.setImageUrl("");

         Product expectedProduct = new Product();
         expectedProduct.setDescription("Large disc");
         expectedProduct.setPrice(50.99);
         expectedProduct.setTitle("Disc");
         expectedProduct.setType("Computer");
         expectedProduct.setImageUrl("assets/images/" + "");

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
    void updateProductById() {
        ProductDTO product = new ProductDTO();
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("assets/images/" + "");

        Product productToUpdate = new Product();
        productToUpdate.setId(1L);
        productToUpdate.setDescription("Large disc");
        productToUpdate.setPrice(51.20);
        productToUpdate.setTitle("Disc");
        productToUpdate.setType("Computer");
        productToUpdate.setImageUrl("assets/images/" + "");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setDescription("Large disc");
        expectedProduct.setPrice(50.99);
        expectedProduct.setTitle("Disc");
        expectedProduct.setType("Computer");
        expectedProduct.setImageUrl("assets/images/" + "");


        when(productRepository.getById(1L)).thenReturn(productToUpdate);

        productService.updateProductById(1L, product);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(expectedProduct);
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
