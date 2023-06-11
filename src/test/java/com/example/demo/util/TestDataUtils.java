package com.example.demo.util;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserRoleEntity;
import com.example.demo.model.enums.StateEnum;
import com.example.demo.model.enums.UserRoleEnum;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataUtils {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    private NewsRepository newsRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         NewsRepository newsRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.newsRepository = newsRepository;
    }

    // initializing the user roles if they are not already present in the userRoleRepository
    // Checking the count of existing roles and creates user roles if count is 0
    private void initRoles() {
        if(userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity publisherRole = new UserRoleEntity().setUserRole(UserRoleEnum.PUBLISHER);
            UserRoleEntity readerRole = new UserRoleEntity().setUserRole(UserRoleEnum.READER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(publisherRole);
            userRoleRepository.save(readerRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        // Calling method to ensure the user roles are initialized
        initRoles();

        // Creating a new UserEntity with provided Email and setting all other properties
        // Assigning the all available user roles
        // Saving the user entity and returning the created UserEntity object
        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Admin").
                setLastName("Adminov").
                setActive(true).
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestPublisher(String email) {

        initRoles();

        var publisher = new UserEntity().
                setEmail(email).
                setFirstName("Publisher").
                setLastName("Publishov").
                setActive(true).
                setUserRoles(userRoleRepository.findAll().stream().filter(r -> r.getUserRole() != UserRoleEnum.ADMIN && r.getUserRole() != UserRoleEnum.READER).toList());

        return userRepository.save(publisher);
    }

    public UserEntity createTestReader(String email) {

        initRoles();

        var reader = new UserEntity().
                setEmail(email).
                setFirstName("Reader").
                setLastName("Readov").
                setActive(true).
                setUserRoles(userRoleRepository.findAll().stream().filter(r -> r.getUserRole() != UserRoleEnum.ADMIN && r.getUserRole() != UserRoleEnum.PUBLISHER).toList());

        return userRepository.save(reader);
    }

    public NewsEntity createTestNews(UserEntity publisher) {

        // Creating a news test entity and assigning the publisher to the news entity
        var newsEntity = new NewsEntity().
                setTitle("Title").
                setCreationDate(LocalDate.now()).
                setValidFrom(LocalDate.now()).
                setValidTo(LocalDate.now()).
                setText("Text").
                setState(StateEnum.NEW).
                setPhotoLink("http://image.com/image").
                setPublisher(publisher);

        return newsRepository.save(newsEntity);
    }

    //Ensuring a clean state of DB before running the tests
    //clearing all entities stored in DB tables related to news, users and user roles

    //Doing so helps isolate the tests from previous state and makes sure the cases are independent and predictable
    public void cleanDatabase() {
        newsRepository.deleteAll();;
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
}
