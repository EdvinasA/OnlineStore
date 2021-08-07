package sda.store.onlinestore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sda.store.onlinestore.exceptions.FoundException;
import sda.store.onlinestore.model.User;
import sda.store.onlinestore.model.UserDTO;
import sda.store.onlinestore.model.UserForLogin;
import sda.store.onlinestore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean loginByUserNameAndPassword(UserForLogin userDTO) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByUserNameAndPasswordIgnoreCase(userDTO.getUserName(), userDTO.getPassword()));
        User user = userOptional.orElseThrow(() -> new RuntimeException("User was not found"));
        return user.getUserName().equals(userDTO.getUserName()) && user.getPassword().equals(userDTO.getPassword());
    }

    public User registerNewUser (UserDTO userDTO) {
        User userOpt = userRepository.findUserByUserNameIgnoreCase(userDTO.getUserName());
        if (userOpt != null) {
            throw new FoundException("User Found");
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public User registerNewAdmin (UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public User getLoggedInUserRoleByUserName(String userName) {
        return userRepository.findUserByUserNameIgnoreCase(userName);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
