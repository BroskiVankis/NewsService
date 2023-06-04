package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserRoleEntity;
import com.example.demo.model.enums.UserRoleEnum;
import com.example.demo.model.user.TechUserDetails;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TechUserDetailsServiceTest {

    // Using the @Mock to create a mock object of the UserRepository
    // Allowing to simulate the behaviour of the real repo
    @Mock
    private UserRepository mockUserRepo;

    // declaring a private field of type "TechUserDetailsService"
    // holding an instance of the "TechUserDetailsService" class that we test
    private TechUserDetailsService toTest;

    //@BeforeEach ->excecuting method before each test method in test class
    @BeforeEach
    void setUp() {
        // Creating new instance, passing the mockUserRepo object as dependency.
        toTest = new TechUserDetailsService(mockUserRepo);
    }

    //Testing loadUserByUsername when user exists
    //Creatng a test user entity,stubbing the behaviour or mock repository
    //asserting the expected result
    @Test
    void testLoadUserByUsername_UserExists() {

        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("Gosho@test.com").
                        setPassword("12345").
                        setFirstName("Gosho").
                        setLastName("Goshev").
                        setActive(true).
                        setImageUrl("http://image.com/image").
                        setUserRoles(
                                List.of(
                                   new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                   new UserRoleEntity().setUserRole(UserRoleEnum.PUBLISHER),
                                   new UserRoleEntity().setUserRole(UserRoleEnum.READER)
                                )
                        );

        // Stubbing the behaviour
        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).
                thenReturn(Optional.of(testUserEntity));

        // Involking with the email of test uer entity.
        // returning UserDetails object cast to TechUserDetails type
        TechUserDetails userDetails = (TechUserDetails)
            toTest.loadUserByUsername(testUserEntity.getEmail());

        // Verifying the returned properties of TechUserDetails object match
        // the corresponding properties of the test user entity
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());

        //Retrieving the authorities form userDetails and asserting that number of auth. is 3
        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(3, authorities.size());

        // Verifying that auth. retrieved from userDetails object match the auth.
        // based on the test user entity user roles
        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.PUBLISHER.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.READER.name(),
                authoritiesIter.next().getAuthority());
    }

    //Testing when user does not exist
    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("Not-existing@test.com")
        );
    }
}


