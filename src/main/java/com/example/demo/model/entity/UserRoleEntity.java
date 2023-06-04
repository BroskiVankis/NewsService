package com.example.demo.model.entity;

import com.example.demo.model.enums.UserRoleEnum;
//import jakarta.persistence.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum  userRole;


    @ManyToMany(mappedBy = "userRoles")
    private List<UserEntity> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public UserRoleEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", users=" + users +
                '}';
    }
}
