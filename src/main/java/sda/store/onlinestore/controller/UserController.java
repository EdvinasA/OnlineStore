package sda.store.onlinestore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.model.UserForLogin;
import sda.store.onlinestore.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://digitalists.herokuapp.com"}, allowedHeaders = "*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserForLogin user) {
        System.out.println(user);
        return userService.loginByUserNameAndPassword(user);
    }

    @PostMapping("/register-user")
    public User registerUser(@RequestBody UserDTO user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/register-admin")
    public User registerAdmin(@RequestBody UserDTO user) {
        return userService.registerNewAdmin(user);
    }

    @GetMapping(value = "/get-role/{userName}")
    public User getRoleByUserName(@PathVariable String userName) {
        return userService.getLoggedInUserRoleByUserName(userName);
    }

    @GetMapping(value = "/find-all-users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<Void> deleteCartProductById(@PathVariable Long userId) {
        System.out.println(userId);
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
    }
}
