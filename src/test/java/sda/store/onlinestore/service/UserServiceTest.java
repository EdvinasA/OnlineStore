package sda.store.onlinestore.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.repository.CartRepository;
import sda.store.onlinestore.repository.ProductRepository;
import sda.store.onlinestore.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private final UserService userService = new UserService(this.userRepository);

//    @Test
//    void deleteUserById() {
//        User user = new User();
//        user.setUserName("edvinas");
//        user.setPassword("edvinas");
//        user.setEmail("edvinasalimas98@gmail.com");
//        user.setAge(22);
//        user.setId(1L);
//        user.setFirstName("Edvinas");
//        user.setLastName("Alimas");
//        user.setRole("USER");
//
//
//    }
}
