package com.example.demo.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users") // base PATH URL -> All endpoints in this controller will be mapped under "/users" path
public class UserController {

    // handles HTTP GET requests to "/users/login" endpoint
    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    // HTTP POST Requests to "/users/login-error" endpoint
    @PostMapping("login-error")
    // "@ModelAttribute" value will be retrieved from the model attribute named: -> username entered in the login form
    // "RedirectAttributes" allows to "addFlashAttributes" used to store data temporarily and make it available in the redirect requests
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        // redirecting, which will trigger the "login()" method defined above
        return "redirect:/users/login";
    }
}