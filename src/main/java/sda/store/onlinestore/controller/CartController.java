package sda.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.store.onlinestore.model.Cart;
import sda.store.onlinestore.model.CartDTO;
import sda.store.onlinestore.service.CartService;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/add")
    public Cart addProductToCart(@RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(cartDTO);
    }
}
