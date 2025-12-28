package crud.service;

import crud.exception.UserAlreadyExistException;
import crud.exception.UserNotFoundException;
import crud.model.Role;
import crud.repository.RoleRepository;
import crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import crud.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(user.getUsername());
        }
        Role role = roleRepository.findByRole("ROLE_USER").get();
        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public void updateUserById(int id, User user, int roleId) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            Role role = roleRepository.findById(roleId).get();
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

    }

}
