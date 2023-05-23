package com.example.demo.web;

import com.example.demo.model.dto.user.UserRegisterDTO;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;

    private LocaleResolver localeResolver;

    public UserRegistrationController(UserService userService, LocaleResolver localeResolver) {
        this.userService = userService;
        this.localeResolver = localeResolver;
    }

    //Method returning DTO representing user registration data
    @ModelAttribute("userModel") // adding model attribute named "userModel" to the model
    public UserRegisterDTO initUserModel() {
        return new UserRegisterDTO();
    }

    // rendering the view for registration
    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userModel, // "@Valid" trigger validations defined in the "UserRegisterDTO"
                           BindingResult bindingResult, // holding the results of the validation -> capturing errors in the validation
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) { // representing the current HTTP request made by user

        // Checking for validation errors
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel); // Data being preserved and accessed in redirected request
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        // if no errors registering and logging the user and resolving the locale based on the request
        this.userService.registerAndLogin(userModel, localeResolver.resolveLocale(request));

        // Redirecting to Home Page
        return "redirect:/";
    }
}
