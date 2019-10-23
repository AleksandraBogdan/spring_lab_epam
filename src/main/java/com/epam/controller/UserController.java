package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserController {
    private  UserService userService;
    public Optional<User> signInUser;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void signUp(User user) {
        userService.signUp(user);
    }

    public void signIn(User user) {
        signInUser = userService.signIn(user);
    }

    public void subscribe(User user) {
        userService.subscribe(user.getEmail());
    }
}
