package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void updateUserById(int id, User user);

    void deleteUserById(int id);


}
