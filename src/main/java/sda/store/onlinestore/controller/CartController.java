package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.exceptions.NotFoundException;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.service.CartService;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart addProductToCart(@RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(cartDTO);
    }

    @GetMapping
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping(value = "/{id}")
    public Cart getCartEntryById(@PathVariable Long cartId){
        return cartService.getCartEntryById(cartId);
    }

    @GetMapping(value = "/getTotalPrice")
    public Double getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteCartProductById(@PathVariable Long id) {
        if (cartService.getCartEntryById(id) == null) {
            throw new NotFoundException("Product not found, productId: " + id);
        } else {
            cartService.getCartEntryById(id);
            return ResponseEntity.ok().build();
        }
    }
}
