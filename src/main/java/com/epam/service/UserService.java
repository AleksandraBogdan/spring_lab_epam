package com.epam.service;

import com.epam.model.User;

public interface UserService {
    void signUp(User user);

    User signIn(User user);

    void subscribe(User user);

}
