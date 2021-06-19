package sda.store.onlinestore.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.OnlineStoreApplicationTests;
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
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class CartServiceTest extends OnlineStoreApplicationTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService; //= new CartService(this.cartRepository, this.productRepository);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
        //this.cartService = new CartService(this.cartRepository, this.productRepository);
    }

    @Test
    void when_product_exists_addOrUpdateCart_should_update(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(1L);
        cartDTO.setQuantity(4.0);

        Cart cartExpecting = new Cart();
        cartExpecting.setId(1L);
        cartExpecting.setProduct(product);
        cartExpecting.setQuantity(4.0);


        when(cartService.isProductExistsInCart(anyLong())).thenReturn(true);
        when(cartRepository.getByProductId(anyLong())).thenReturn(cart);
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cartExpecting);

        Cart result = cartService.addOrUpdateCart(cartDTO);

        assertThat(result).isEqualTo(cartExpecting);
    }

    @Test
    void when_product_does_not_exist_addOrUpdateCart_should_add(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(2L);
        cartDTO.setQuantity(4.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Wire");
        product2.setDescription("Long wire");
        product2.setPrice(20.99);
        product2.setImageUrl("");
        product2.setType("Other");

        Cart cartExpecting = new Cart();
        cartExpecting.setId(2L);
        cartExpecting.setProduct(product2);
        cartExpecting.setQuantity(4.0);

        when(cartService.isProductExistsInCart(anyLong())).thenReturn(false);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product2));
        when(cartRepository.save(any(Cart.class))).thenReturn(cartExpecting);

        Cart result = cartService.addOrUpdateCart(cartDTO);

        assertThat(result).isEqualTo(cartExpecting);
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

        Cart cart = new Cart();
        cart.setId(3L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart created = cartService.addProductToCart(cartDTO);

        assertThat(created).isSameAs(cart);
    }

    @Test
    void when_updateCartProductById_it_should_return_cart() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProduct(product);
        cart.setQuantity(3.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Wire");
        product2.setDescription("Long wire");
        product2.setPrice(20.99);
        product2.setImageUrl("");
        product2.setType("Other");

        Cart cartExpecting = new Cart();
        cartExpecting.setId(1L);
        cartExpecting.setProduct(product2);
        cartExpecting.setQuantity(4.0);

        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cartExpecting);

        Cart result = cartService.updateCartProductById(cartExpecting);

        assertThat(result).isEqualTo(cartExpecting);
    }

    @Test
    void when_updateCartProductById_and_quantity_less_than_zero_it_should_update_to_zero_and_return_cart() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cartBefore = new Cart();
        cartBefore.setId(1L);
        cartBefore.setProduct(product);
        cartBefore.setQuantity(3.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Wire");
        product2.setDescription("Long wire");
        product2.setPrice(20.99);
        product2.setImageUrl("");
        product2.setType("Other");

        Cart cartInput= new Cart();
        cartInput.setId(2L);
        cartInput.setProduct(product);
        cartInput.setQuantity(-3.0);

        Cart cartExpecting = new Cart();
        cartExpecting.setId(1L);
        cartExpecting.setProduct(product2);
        cartExpecting.setQuantity(0.0);

        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cartBefore));
        when(cartRepository.save(any(Cart.class))).thenReturn(cartExpecting);

        Cart result = cartService.updateCartProductById(cartInput);

        assertThat(result).isEqualTo(cartExpecting);
        assertThat(result.getQuantity()).isEqualTo(0.0);
    }


    @Test
    void given_new_product_isProductExistsInCart_should_return_false() {
        when(cartRepository.existsCartByProductId(anyLong())).thenReturn(false);

        boolean result = cartService.isProductExistsInCart(1L);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void given_existing_product_isProductExistsInCart_should_return_true() {
        when(cartRepository.existsCartByProductId(anyLong())).thenReturn(true);

        boolean result = cartService.isProductExistsInCart(1L);

        assertThat(result).isEqualTo(true);
    }
    @Test
    void when_deleteCartProductById() {
        cartService.deleteCartProductById(1L);

        verify(cartRepository, times(1)).deleteById(1L);
    }

    @Test
    void when_quantity_0_or_less_return_true(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cartInput1= new Cart();
        cartInput1.setId(1L);
        cartInput1.setProduct(product);
        cartInput1.setQuantity(-3.0);

        Cart cartInput2= new Cart();
        cartInput2.setId(1L);
        cartInput2.setProduct(product);
        cartInput2.setQuantity(0.0);

        boolean result1 = cartService.checkIfCartIsZeroOrLess(cartInput1);
        boolean result2 = cartService.checkIfCartIsZeroOrLess(cartInput2);

        assertThat(result1).isEqualTo(true);
        assertThat(result2).isEqualTo(true);

    }
    @Test
    void when_quantity_more_than_0_return_false(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Disc");
        product.setDescription("Large disc");
        product.setPrice(50.99);
        product.setImageUrl("");
        product.setType("Computer");

        Cart cartInput1= new Cart();
        cartInput1.setId(1L);
        cartInput1.setProduct(product);
        cartInput1.setQuantity(3.0);

        boolean result1 = cartService.checkIfCartIsZeroOrLess(cartInput1);

        assertThat(result1).isEqualTo(false);

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
