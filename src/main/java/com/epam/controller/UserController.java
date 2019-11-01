package com.epam.controller;


import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void signUp(User user) {
        userService.signUp(user);
    }

    public void signIn(User user) {
        userService.signIn(user);
    }

    public void subscribe(User user) {
        userService.subscribe(user);
    }


    public List<User> showAll() {
        return userService.findAll();
    }
}
