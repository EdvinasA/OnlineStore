package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO user) {
        return userService.loginByUserNameAndPassword(user);
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
