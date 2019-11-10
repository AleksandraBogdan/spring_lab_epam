package com.epam.controller;


import com.epam.dto.UserDto;
import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void signUp(UserDto user) {
        userService.signUp(user);
    }

    @GetMapping("/user/{id}")
    public void signIn(@PathVariable Long id, UserDto user) {
        userService.signIn(user);
    }

    @PutMapping("/subscribe/{id}")
    public void subscribe(@PathVariable Long id, UserDto user) {
        userService.subscribe(user);
    }

    @GetMapping("/showAllUsers")
    public List<UserDto> showAll() {
        return userService.findAll();
    }
}
