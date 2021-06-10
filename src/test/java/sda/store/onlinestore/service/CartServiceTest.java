package sda.store.onlinestore.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository = mock(CartRepository.class, Mockito.RETURNS_DEEP_STUBS);

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }

    @Test
    void when_addProductToCart_it_should_return_cart() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(1L);
        cartDTO.setQuantity(3.0);

        when(productRepository.findProductById(1L)).thenReturn(Optional.of(new Product()));
        when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(new Cart());

        Cart created = cartService.addProductToCart(cartDTO);

        assertThat(created.getProduct().getId()).isSameAs(cartDTO.getProductId());

    }

    @Test
    void checkIfProductExists() {
    }

    @Test
    void deleteCartProductById() {
    }

    @Test
    void getAllCart() {

        List<Cart> list = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setDescription("test");
        product.setPrice(2.0);
        product.setTitle("test");
        product.setType("test");
        product.setImageUrl("test");
        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setProduct(product);
        cart1.setQuantity(3.0);

        list.add(cart1);
        //cartRepository.save(cart1);
        //when(cartRepository.save(any(Cart.class))).thenReturn(new Cart());
        //CartRepository myService = mock(CartRepository.class, Mockito.RETURNS_DEEP_STUBS);
        when(this.cartRepository.findAll()).thenReturn(list);

        //test
        List<Cart> cartList = cartService.getAllCart();

        assertEquals(1, cartList.size());
        verify(cartService, times(1)).getAllCart();
    }

    @Test
    void addProductQuantityInCart() {
    }

    @Test
    void subtractProductQuantityInCart() {
    }

    @Test
    void checkIfCartIsZero() {
    }

    @Test
    void getCartEntryById() {
    }

    @Test
    void getTotalPrice() {
    }
}
