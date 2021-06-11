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
import sda.store.onlinestore.repository.ProductQuantityRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
class ProductServiceTest extends OnlineStoreApplicationTests {

    @InjectMocks
    public ProductService productService;

    @Mock
    public ProductRepository productRepository;
//    public ProductQuantityRepository productQuantityRepository;


    @Test
    void whenRegisteredNewProduct_findCreatedProductById() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Computer");
        productDTO.setPrice(22.53);
        Product product = productService.newProductRegistration(productDTO);

        Mockito.when(this.productRepository.findProductById(1L)).thenReturn(java.util.Optional.ofNullable(product));
        Product product1 = productService.getProductById(1L);

        assertThat(product1).isEqualTo(product);
    }

    @Test
    void getAllProducts() {
        Product product = new Product();
        Product product2 = new Product();
        product.setId(1L);
        product2.setId(2L);
        List<Product> expectedProduct = new ArrayList<>();
        expectedProduct.add(product);
        expectedProduct.add(product2);

        Mockito.when(this.productRepository.findAll()).thenReturn(expectedProduct);
        List<Product> product1 = productService.getAllProducts();

        assertThat(product1).isEqualTo(expectedProduct);
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);

        Mockito.when(this.productRepository.findProductById(1L)).thenReturn(java.util.Optional.of(product));
        Product product1 = productService.getProductById(1L);

        assertThat(product1).isEqualTo(product);
    }

    @Test
    void return_empty_when_ProductList_Empty() {
        List<Product> expected = new ArrayList<>();
        Mockito.when(this.productRepository.findAll()).thenReturn(expected);
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

        Mockito.when(this.productRepository.findProductsByTitleIgnoreCase("Computer")).thenReturn(expectedProducts);
        List<Product> product1 = productService.getProductsByTitle("Computer");

        assertThat(product1).isEqualTo(expectedProducts);
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void updateProductById() {
    }

    @Test
    void getAllProductQuantityOnDate() {
    }

    @Test
    void getQuantityByProductIdOnDate() {
    }
}
