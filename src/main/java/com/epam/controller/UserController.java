package com.epam.controller;


import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @GetMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public User signIn(@PathVariable Long id, User user) {
        return userService.signIn(user);
    }

    @PutMapping("/subscribe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void subscribe(@PathVariable Long id, User user) {
        userService.subscribe(user);
    }

    @GetMapping("/showAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<User> showAll() {
        return userService.findAll();
    }
}
