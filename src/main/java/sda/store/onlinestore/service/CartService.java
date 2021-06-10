package sda.store.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.model.Product;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addProductToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        Optional<Product> productOpt = productRepository.findById(cartDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
                if (checkIfProductExists(cartDTO)) {
                    return null;
                }
                else {
            cart.setProduct(product);
            cart.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cart);
            return cart;
        }
    }

    public boolean checkIfProductExists(CartDTO cartDTO) {
        List<Cart> allProductsInCart = getAllCart();
        for (Cart cart: allProductsInCart) {
            if (cart.getProduct().getId().equals(cartDTO.getProductId())) {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }

    public void deleteCartProductById(Long id) {
        cartRepository.deleteById(id);
    }

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public void addProductQuantityInCart(Long cartId) {
        Cart cart = getCartEntryById(cartId);
        cart.setQuantity(cart.getQuantity() + 1);
        cartRepository.save(cart);
    }

    public void subtractProductQuantityInCart(Long cartId) {
        Cart cart = getCartEntryById(cartId);
        if (checkIfCartIsZero(cart)) {
            cart.setQuantity(0.00);
            return;
        }
        cart.setQuantity(cart.getQuantity() - 1);
        cartRepository.save(cart);
    }

    public boolean checkIfCartIsZero(Cart cart) {
        return cart.getQuantity() <= 0;
    }

    public Cart getCartEntryById(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        return cartOpt.orElseThrow(() -> new RuntimeException("Cart entry was nor found"));
    }

    public Double getTotalPrice() {
        double sum = 0;
        List<Cart> allProducts = new ArrayList<>();
        allProducts = getAllCart();
        for (Cart cart:
             allProducts) {
           double productSum = cart.getProduct().getPrice() * cart.getQuantity();
           sum += productSum;
        }
        return sum;
    }
}
