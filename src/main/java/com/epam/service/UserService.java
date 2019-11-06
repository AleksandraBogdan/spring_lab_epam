package com.epam.service;

import com.epam.model.User;

import java.util.List;

public interface UserService {
    void signUp(User user);

    User signIn(User user);

    void subscribe(User user);

    List<User> findAll();
}
