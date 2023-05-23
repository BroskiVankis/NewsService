package com.example.demo.service;

import com.example.demo.model.dto.user.UserRegisterDTO;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private UserDetailsService userDetailsService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO, Locale preferredLocale) {

        //Creating a new instance of USerEntity by using mapper method
        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        // Setting and encoding the password
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser.getEmail());
    }

    public void login(String userName) {
        // Loading the user details from the DB
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        // Creating auth token -> representing the auth info for the user
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        // Setting the auth Object in the security context
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void createUserIfNotExist(String email) {
        var userOpt = this.userRepository.findByEmail(email); // Searching fot a user in the DB from the provided Email
        if(userOpt.isEmpty()) {
            var newUser = new UserEntity().setEmail(email).setPassword(null).setFirstName("New").setLastName("User").setUserRoles(List.of());
            userRepository.save(newUser);
        }
    }

}
