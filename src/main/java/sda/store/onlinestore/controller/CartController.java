package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.service.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/add")
    public Cart addProductToCart(@RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(cartDTO);
    }
}
