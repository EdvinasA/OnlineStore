package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = {"http://localhost:4200", "https://digitalists.herokuapp.com"}, allowedHeaders = "*")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/{userId}")
    public Cart addProductToCart(@Valid @RequestBody CartDTO cartDTO, @PathVariable String userId){
        return cartService.addProductToCart(cartDTO, Long.parseLong(userId));
    }

    @GetMapping(value = "/{userId}")
    public List<Cart> getAllCart(@PathVariable String userId){
        if (userId.equals("null")) {
            return null;
        }
        return cartService.getAllCartByUserId(Long.parseLong(userId));
    }

    @GetMapping(value = "/getTotalPrice/{userId}")
    public Double getTotalPrice(@PathVariable String userId) {
        return cartService.getTotalPriceByUserId(Long.parseLong(userId));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCartProductById(@PathVariable Long id) {
        if (cartService.getCartEntryById(id) == null) {
            throw new NotFoundException("Cart not found, CartId: " + id);
        } else {
            cartService.deleteCartProductById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping
    public ResponseEntity<Cart> updateCartProductById(@RequestBody Cart cart) {
            return new ResponseEntity(cartService.updateCartProductById(cart), HttpStatus.OK);
    }
}
