package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.service.CartService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping()
    public Cart addProductToCart(@Valid @RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(cartDTO);
    }

    @PostMapping(value = "/add-quantity")
    public void addQuantityToCartProduct(@RequestBody Cart cart) {
        cartService.addProductQuantityInCart(cart.getId());
    }

    @PostMapping(value = "/subtract-quantity")
    public void subtractProductQuantityInCart(@RequestBody Cart cart) {
        cartService.subtractProductQuantityInCart(cart.getId());
    }

    @GetMapping
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping(value = "/getTotalPrice")
    public Double getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteCartProductById(@PathVariable Long id) {
        if (cartService.getCartEntryById(id) == null) {
            throw new NotFoundException("Cart not found, CartId: " + id);
        } else {
            cartService.deleteCartProductById(id);
            return ResponseEntity.ok().build();
        }
    }
}
