package sda.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.store.onlinestore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserNameAndPasswordIgnoreCase(String userName, String password);
    User findUserByUserNameIgnoreCase(String userName);
}
