package sda.store.onlinestore.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;


import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.runners.MockitoJUnitRunner;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    //


    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService = new CartService(this.cartRepository, this.productRepository);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
        this.cartService = new CartService(this.cartRepository, this.productRepository);
    }

    @Test
    void when_addProductToCart_it_should_return_cart() {
        Product product = new Product();
        product.setId(3L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");


        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(3L);
        cartDTO.setQuantity(3.0);

        Cart cart = getCart();
        Cart cartWithoutId = getCartWithoutId();

        when(productRepository.findById(cartDTO.getProductId())).thenReturn(Optional.of(product));
        when(cartRepository.save(cartWithoutId)).thenReturn(cartWithoutId);

        Cart created = cartService.addProductToCart(cartDTO);

        //assertThat(created).isSameAs(cart);

    }

    @Test
    void checkIfProductExists() {
    }

    @Test
    void deleteCartProductById() {
    }

    @Test
    void when_getAllCart_it_should_return_cart_list() {
        List<Cart> list = getCartList();

        when(this.cartRepository.findAll()).thenReturn(list);

        List<Cart> cartList = cartService.getAllCart();

        assertEquals(1, cartList.size());
        assertThat(cartList).isSameAs(list);
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
        Cart cart = getCart();

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartEntryById(1L);
        assertThat(cart).isSameAs(result);
    }



    @Test
    void getTotalPrice() {
    }

    @NotNull
    private List<Cart> getCartList() {
        List<Cart> cartList = new ArrayList<>();

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

        cartList.add(cart);
        return cartList;
    }

    @NotNull
    private Cart getCart() {
        Product product = new Product();
        product.setId(3L);
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("");
        Cart cart = new Cart();
        cart.setId(3L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        return cart;
    }

    @NotNull
    private Cart getCartWithoutId() {
        Product product = new Product();
        product.setId(3L);
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setTitle("Disc");
        product.setType("Computer");
        product.setImageUrl("");
        Cart cart = new Cart();
        cart.setId(3L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        return cart;
    }

}
