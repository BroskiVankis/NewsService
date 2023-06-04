package com.example.demo.web;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //Configuring and autowiring the MockMvc instance for performing HTTP requests and asserting response
public class UserRegistrationControllerMockBeanIT {

    @Autowired
    private MockMvc mockMvc;

    // Performing a GET request to "/users/register"
    //andExpect method is asserting the expected behaviour of the response
    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("auth-register"));
    }

    // Performing a POST request to the same URL
    // param() method simulates from input
    // Setting a cookie with lang name and value of German language
    //with(csrf()) -> adding CSRF token to the request to protect against Cross Site request attacks
    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                param("email", "ivan@example.com").
                param("firstName", "Ivan").
                param("lastName", "Ivanov").
                param("password", "12345").
                param("confirmPassword", "12345").
                cookie(new Cookie("lang", Locale.GERMAN.getLanguage())).with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }
}
