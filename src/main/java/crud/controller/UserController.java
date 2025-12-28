package crud.controller;

import crud.dto.UserDTO;

import crud.dto.UserRegistrationDTO;
import crud.dto.UserUpdateDTO;
import crud.mapper.UserMapper;
import crud.model.Role;
import crud.model.User;
import crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userMapper.toUserDTO(user);
        model.addAttribute("user", userDTO);
        return "user";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = userMapper.toUserDTOs(users);
        model.addAttribute("users", userDTOS);
        return "users";
    }

    @GetMapping("/newUser")
    public String getNewUserForm(Model model) {
        User user = new User();
        UserRegistrationDTO userRegistrationDTO = userMapper.toUserRegistrationDTO(user);
        model.addAttribute("user", userRegistrationDTO);
        return "newUser";
    }

    @PostMapping("/newUser")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO) {
        User user = userMapper.toUserRegistration(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String getEditUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        UserUpdateDTO userDTO = userMapper.toUserUpdateDTO(user);
        model.addAttribute("user", userDTO);
        return "editUser";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @Valid @ModelAttribute("user") UserUpdateDTO userUpdateDTO) {
        User user = userService.getUserById(id);
        userMapper.updateUser(userUpdateDTO, user);
        userService.updateUserById(id, user, userUpdateDTO.role());
        return "redirect:/admin/users";
    }

}
