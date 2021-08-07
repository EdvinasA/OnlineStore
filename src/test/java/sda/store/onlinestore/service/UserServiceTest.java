package sda.store.onlinestore.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserForLogin;
import sda.store.onlinestore.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

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
    @Disabled
    void registerNewUser() {
    }

    @Test
    @Disabled
    void registerNewAdmin() {
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
        userRepository.findAll();

        verify(userRepository).findAll();
    }
}
