package crud.controller;

import crud.model.User;
import crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController  {
    private final UserService userService;
    @GetMapping("users/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @GetMapping("/newUser")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }
    @PostMapping("/newUser")
    public String saveUser(@ModelAttribute("user") User user) {
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
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }
    @PatchMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute("user") User user) {
        userService.updateUserById(id, user);
        return "redirect:/users";
    }

}
