package sda.store.onlinestore.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.repository.UserRepository;
import sda.store.onlinestore.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void login() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void registerAdmin() {
    }

    @Test
    void getRoleByUserName() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void deleteCartProductById() {
    }
}
