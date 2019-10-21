package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserController {
    private  UserService userService;

    public void signUp(User user) {
        userService.signUp(user);
    }

    public void signIn(User user) {
        Optional<User> signInUser = userService.signIn(user);
    }
}
