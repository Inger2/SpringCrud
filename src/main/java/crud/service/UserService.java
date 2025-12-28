package crud.service;

import crud.model.Role;
import crud.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void updateUserById(int id, User user, int roleId);

    void deleteUserById(int id);


}
