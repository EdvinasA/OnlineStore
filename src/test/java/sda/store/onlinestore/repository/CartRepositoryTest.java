package sda.store.onlinestore.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CartRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;


    @Test
    void findAllByUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        Product product = new Product();
        product.setId(3L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);
        cart.setUser(user);

        List<Cart> cartList = Collections.singletonList(cart);

        doReturn(cartList).when(cartRepository).findAllByUser(user);


    }
}
