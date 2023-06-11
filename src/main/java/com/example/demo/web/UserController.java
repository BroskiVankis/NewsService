package com.example.demo.web;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.UserServiceImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users") // base PATH URL -> All endpoints in this controller will be mapped under "/users" path
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        super();
        this.userServiceImpl = userServiceImpl;
    }

    // handles HTTP GET requests to "/users/login" endpoint and rendering the "auth-login" view
    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    // HTTP POST Requests to "/users/login-error" endpoint
    @PostMapping("/login-error")
    // "@ModelAttribute" value will be retrieved from the model attribute named: -> username entered in the login form
    // "RedirectAttributes" allows to "addFlashAttributes" used to store data temporarily and make it available in the redirect requests
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        // redirecting, which will trigger the "login()" method defined above
        return "redirect:/users/login";
    }

    //Rendering the view users-all where all the users are displayed in a table
    //This is when an ADMIN would want to make some changes to the existing users
    // All the controllers from here down are only for ADMIN user to perform
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());

        return "users-all";
    }

    //Renderin the create-user view where the ADMIN can create a new user
    @GetMapping("/new")
    public String createNewUser(Model model) {

        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "create-user";
    }

    //Post request where a new user is being created
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userServiceImpl.saveUser(user);
        return "redirect:/users/users";
    }

    // Rendering the edit-user view where changes can be made regarding the chosen user
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "edit-user";
    }

    //Post request where an already existing user is being updated, changing the values
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") UserEntity user) {

        UserEntity existingUser = userServiceImpl.getUserById(id);
        existingUser.setId(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        userServiceImpl.updateUser(existingUser);

        return "redirect:/users/users";
    }

    //Delete request, where an user is being deleted
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/users/users";
    }
}