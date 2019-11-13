package com.epam.service;

import com.epam.dto.UserDto;
import com.epam.model.User;

import java.util.List;

public interface UserService {
    UserDto signUp(UserDto user);

    UserDto signIn(UserDto user);

    void subscribe(UserDto user);

    List<UserDto> findAll();
}
