package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;

import java.util.List;

public interface UserServiceInterface {

    List<UserEntity> getAllUsers();

    UserEntity saveUser(UserEntity user);

    UserEntity getUserById(Long id);

    UserEntity updateUser(UserEntity user);

    void deleteUserById(Long id);

}
