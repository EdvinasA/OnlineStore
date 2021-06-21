package sda.store.onlinestore.repository;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sda.store.onlinestore.model.Product;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findProductById() {
        Product product = new Product();
        //product.setId(1L);
        product.setImageUrl("image.png");
        product.setTitle("Computer Dell");
        product.setDescription("computer for sale");
        product.setPrice(2000.0);
        product.setType("Computer");
        productRepository.save(product);

        Optional<Product> result = productRepository.findProductById(1L);

        assertThat(result.get().getDescription()).isEqualTo(product.getDescription());

    }

    @Test
    void findProductsByTitleIgnoreCase() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getById() {
    }
}
