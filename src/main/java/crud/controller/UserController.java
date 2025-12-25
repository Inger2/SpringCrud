package crud.controller;

import crud.dto.UserDTO;
import crud.dto.mapper.UserMapper;
import crud.model.User;
import crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("users/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userMapper.toUserDTO(user);
        model.addAttribute("user", userDTO);
        return "user";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = userMapper.toUserDTOs(users);
        model.addAttribute("users", userDTOS);
        return "users";
    }

    @GetMapping("/newUser")
    public String getNewUserForm(Model model) {
        User user = new User();
        UserDTO userDTO = userMapper.toUserDTO(user);
        model.addAttribute("user", userDTO);
        return "newUser";
    }

    @PostMapping("/newUser")
    public String saveUser(@Valid @ModelAttribute("user") UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String getEditUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userMapper.toUserDTO(user);
        model.addAttribute("user", userDTO);
        return "editUser";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @Valid @ModelAttribute("user") UserDTO userDTO) {
        User user = userService.getUserById(id);
        userMapper.updateUser(userDTO, user);
        userService.updateUserById(id, user);
        return "redirect:/users";
    }

}
