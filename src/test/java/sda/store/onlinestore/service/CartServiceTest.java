package sda.store.onlinestore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.OnlineStoreApplicationTests;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest extends OnlineStoreApplicationTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService = new CartService(this.cartRepository, this.productRepository, this.userRepository);

//    @Test
//    void when_addProductToCart_it_should_return_cart() {
//        Product product = new Product();
//        product.setId(3L);
//        product.setTitle("Disc");
//        product.setDescription("Large disc");
//        product.setPrice(50.99);
//        product.setImageUrl("");
//        product.setType("Computer");
//
//        CartDTO cartDTO = new CartDTO();
//        cartDTO.setProductId(3L);
//        cartDTO.setQuantity(3.0);
//
//        Cart cart = new Cart();
//        cart.setId(3L);
//        cart.setProduct(product);
//        cart.setQuantity(3.0);
//
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
//        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//        Cart created = cartService.addProductToCart(cartDTO);
//
//        assertThat(created).isSameAs(cart);
//    }

    @Test
    void given_new_product_return_false() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(3L);
        cartDTO.setQuantity(3.0);

        when(cartService.getAllCart()).thenReturn(cartList);

        boolean result = cartService.checkIfProductExists(cartDTO);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void given_existing_product_return_true() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Large disc");
        product1.setPrice(50.99);
        product1.setTitle("Disc");
        product1.setType("Computer");
        product1.setImageUrl("");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Long cabel");
        product2.setPrice(20.99);
        product2.setTitle("Cabel");
        product2.setType("Other");
        product2.setImageUrl("");

        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setProduct(product1);
        cart1.setQuantity(1.0);

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setProduct(product2);
        cart2.setQuantity(2.0);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(2L);
        cartDTO.setQuantity(2.0);

        when(cartService.getAllCart()).thenReturn(cartList);
        when(cartRepository.save(cart2)).thenReturn(cart2);

        boolean result = cartService.checkIfProductExists(cartDTO);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void when_getAllCart_it_should_return_cart_list() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        when(this.cartRepository.findAll()).thenReturn(cartList);

        List<Cart> result = cartService.getAllCart();

        assertEquals(1, cartList.size());
        assertThat(result).isSameAs(cartList);
    }

    @Test
    void when_getCartEntryById_return_Cart_entry_with_specified_id() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartEntryById(1L);
        assertThat(cart).isSameAs(result);
    }

    @Test
    void given_cart_products_return_total_price_92_97() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("Large disc");
        product1.setPrice(50.99);
        product1.setTitle("Disc");
        product1.setType("Computer");
        product1.setImageUrl("");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Long cabel");
        product2.setPrice(20.99);
        product2.setTitle("Cabel");
        product2.setType("Other");
        product2.setImageUrl("");

        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setProduct(product1);
        cart1.setQuantity(1.0);

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setProduct(product2);
        cart2.setQuantity(2.0);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);

        when(cartService.getAllCart()).thenReturn(cartList);

        Double result = cartService.getTotalPrice();

        assertThat(result).isEqualTo(92.97);
    }
}
