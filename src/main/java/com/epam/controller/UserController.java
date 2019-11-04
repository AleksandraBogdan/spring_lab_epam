package com.epam.controller;


import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void signUp(User user) {
        userService.signUp(user);
    }

    @GetMapping("/user/{id}")
    public void signIn(@PathVariable Long id, User user) {
        userService.signIn(user);
    }

    @PutMapping("/subscribe/{id}")
    public void subscribe(@PathVariable Long id, User user) {
        userService.subscribe(user);
    }

    @GetMapping("/showAllUsers")
    public List<User> showAll() {
        return userService.findAll();
    }
}
