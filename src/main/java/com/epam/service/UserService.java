package com.epam.service;

import com.epam.model.Task;
import com.epam.model.User;

import java.util.List;

public interface UserService {
    User signUp(User user);

    User signIn(User user);
}
