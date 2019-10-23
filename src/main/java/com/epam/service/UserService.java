package com.epam.service;

import com.epam.model.User;

import java.util.Optional;

public interface UserService {
    void signUp(User user);

    Optional<User> signIn(User user);

    void subscribe(String email);

}
