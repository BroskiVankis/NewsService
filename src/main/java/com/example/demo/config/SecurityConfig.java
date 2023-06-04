package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.OAuthSuccessHandler;
import com.example.demo.service.TechUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    //Here i will put 3 things
    // 1) PasswordEncoder
    // 2) SecurityFilterChain
    // 3) UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        CharSequence secret = "my-secret";
        int saltLength = 16;
        int iterations = 10000;
        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm = Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;

        return new Pbkdf2PasswordEncoder(secret, saltLength, iterations, secretKeyFactoryAlgorithm);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {
        httpSecurity.
                // defining which requests are allowed and which not
                        authorizeRequests().
                // everyone can download static resources
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can login and register
                requestMatchers("/", "/users/login", "/users/register").permitAll().
                requestMatchers("/news/add").authenticated().
                requestMatchers("/news/**").permitAll().
                requestMatchers("/maintenance").permitAll().
//                requestMatchers("/news/{id}/edit").hasRole("ADMIN").
                // all other pages are available for logged users
                anyRequest().authenticated().and().
                // config of form login
                formLogin().
                // Custom login form
                loginPage("/users/login").
                // Name of username from field
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // Name of Password from field
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // Where to go if login successful
                defaultSuccessUrl("/").
                // Where to go if login failed
                failureForwardUrl("/users/login-error").
                and().
                // config logout
                logout().
                // Logout URL, must be POST
                logoutUrl("/users/logout").
                // After logout redirect to HomePage
                logoutSuccessUrl("/").
                // Invalidate Session and delete cookies
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                and().
                // allow oauth login
                oauth2Login().
                loginPage("/users/login").
                successHandler(oAuthSuccessHandler);

        return httpSecurity.build();
    }

    @Primary
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new TechUserDetailsService(userRepository);
    }
}