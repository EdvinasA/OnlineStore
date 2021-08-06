package sda.store.onlinestore.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void loginByUserNameAndPassword() {
//        User user = new User();
//        user.setId(1L);
//        user.setFirstName("user");
//        user.setLastName("user");
//        user.setAge(22);
//        user.setUserName("user");
//        user.setPassword("user");
//        user.setEmail("user@gmail.com");
//        user.setRole("USER");
//
//        UserDTO userToTest = new UserDTO();
//        userToTest.setFirstName("user");
//        userToTest.setLastName("user");
//        userToTest.setAge(22);
//        userToTest.setUserName("user");
//        userToTest.setPassword("user");
//        userToTest.setEmail("user@gmail.com");
//        userToTest.setRole("USER");
//
//        //when
//        User testingUser = userService.registerNewUser(userToTest);
//
//        //then
//        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
//
//        verify(userRepository).save(userArgumentCaptor.capture());
//
//        User capturedUser = userArgumentCaptor.getValue();
//        capturedUser.setId(1L);
//
//        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void registerNewUser() {
    }

    @Test
    void registerNewAdmin() {
    }

    @Test
    void getLoggedInUserRoleByUserName() {
    }

    @Test
    void findAllUsers() {
    }

    @Test
    void deleteUserById() {
    }
}
