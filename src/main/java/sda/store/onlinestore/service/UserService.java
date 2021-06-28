package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.model.UserForLogin;
import sda.store.onlinestore.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean loginByUserNameAndPassword(UserForLogin userDTO) {
        User user = userRepository.findUserByUserNameAndPasswordIgnoreCase(userDTO.getUserName(), userDTO.getPassword());

        return user.getUserName().equals(userDTO.getUserName()) && user.getPassword().equals(userDTO.getPassword());
    }

    public User registerNewUser (UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
}
