package sda.store.onlinestore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.OnlineStoreApplicationTests;
import sda.store.onlinestore.exceptions.FoundException;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.model.UserForLogin;
import sda.store.onlinestore.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void loginByUserNameAndPassword_returnsTrue() {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        when(userRepository.findUserByUserNameAndPasswordIgnoreCase(user.getUserName(), user.getPassword())).thenReturn(user);
        //when
        boolean userExists = userService.loginByUserNameAndPassword(new UserForLogin("user", "user"));

        //then
        assertThat(userExists).isEqualTo(true);

    }

    @Test
    void loginByUserNameAndPassword_ThrowsRuntimeException() {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        given(userRepository.findUserByUserNameAndPasswordIgnoreCase("users", "users"))
                .willThrow(RuntimeException.class);
        //when
        //then
        assertThatThrownBy(() -> userService.loginByUserNameAndPassword(new UserForLogin("users", "users")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void registerNewUser_ThrowsFoundException_BecauseUserExists() {
        UserDTO user = new UserDTO();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        given(userRepository.findUserByUserNameIgnoreCase("user"))
                .willThrow(FoundException.class);

        //when
        //then
        assertThatThrownBy(() -> userService.registerNewUser(user))
                .isInstanceOf(FoundException.class);
    }

    @Test
    void registerNewUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        User userCheck = new User();
        userCheck.setFirstName("user");
        userCheck.setLastName("user");
        userCheck.setAge(22);
        userCheck.setUserName("user");
        userCheck.setPassword("user");
        userCheck.setEmail("user@gmail.com");
        userCheck.setRole("USER");

        //when
        userService.registerNewUser(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(userCheck);
    }

    @Test
    void registerNewAdmin() {
        UserDTO user = new UserDTO();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        User userCheck = new User();
        userCheck.setFirstName("user");
        userCheck.setLastName("user");
        userCheck.setAge(22);
        userCheck.setUserName("user");
        userCheck.setPassword("user");
        userCheck.setEmail("user@gmail.com");
        userCheck.setRole("USER");

        //when
        userService.registerNewAdmin(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(userCheck);
    }

    @Test
    void getLoggedInUserRoleByUserName() {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        when(userRepository.findUserByUserNameIgnoreCase(user.getUserName())).thenReturn(user);

        //when

        User userExists = userService.getLoggedInUserRoleByUserName("user");

        //then

        assertThat(user).isEqualTo(userExists);
    }

    @Test
    void findAllUsers() {
        //given
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(22);
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@gmail.com");
        user.setRole("USER");

        List<User> expectedUsers = Collections.singletonList(user);

        //when
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> users = userRepository.findAll();

        //then
        assertThat(users).isEqualTo(expectedUsers);
    }
}
