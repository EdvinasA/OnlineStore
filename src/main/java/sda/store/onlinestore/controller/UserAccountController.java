package sda.store.onlinestore.controller;

import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.UserAccount;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "**", allowCredentials = "")
public class UserAccountController {

    @PostMapping(value ="/login" )
    public boolean login(@RequestBody UserAccount user) {
        return
                user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @GetMapping(value ="/user" )
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
