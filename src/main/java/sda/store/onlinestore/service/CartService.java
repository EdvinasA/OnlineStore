package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.*;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart addOrUpdateCart(CartDTO cartDTO){
        if (isProductExistsInCart(cartDTO.getProductId())){
            Cart cart = cartRepository.getByProductId(cartDTO.getProductId());
            cart.setQuantity(cartDTO.getQuantity());
            return updateCartProductById(cart);
        } else {
            return addProductToCart(cartDTO);
        }
    }

    public Cart addProductToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        Optional<Product> productOpt = productRepository.findById(cartDTO.getProductId());
        Product product = productOpt.orElseThrow(() -> new RuntimeException("Product was not found"));
        cart.setProduct(product);
        cart.setQuantity(cartDTO.getQuantity());
        return cartRepository.save(cart);
    }

    public Cart updateCartProductById(Cart cart) {
        Cart cartProductToUpdate = getCartEntryById(cart.getId());
        cartProductToUpdate.setProduct(cart.getProduct());
            if (checkIfCartIsZeroOrLess(cart)) {
                cartProductToUpdate.setQuantity(0.00);
            } else {
                cartProductToUpdate.setQuantity(cart.getQuantity());
            }
        return cartRepository.save(cartProductToUpdate);
    }

    public boolean isProductExistsInCart(Long productId){
        return cartRepository.existsCartByProductId(productId);
    }


/*    public boolean checkIfProductExists(CartDTO cartDTO) {
        List<Cart> allProductsInCart = getAllCart();
        for (Cart cart: allProductsInCart) {
            if (cart.getProduct().getId().equals(cartDTO.getProductId())) {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }*/

    public void deleteCartProductById(Long id) {
        cartRepository.deleteById(id);
    }

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public boolean checkIfCartIsZeroOrLess(Cart cart) {
        return cart.getQuantity() <= 0;
    }

    public Cart getCartEntryById(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        return cartOpt.orElseThrow(() -> new RuntimeException("Cart entry was not found"));
    }

    public Double getTotalPrice() {
        double sum = 0;
        List<Cart> allProducts;
        allProducts = getAllCart();
        for (Cart cart:
             allProducts) {
           double productSum = cart.getProduct().getPrice() * cart.getQuantity();
           sum += productSum;
        }
        return sum;
    }
}
