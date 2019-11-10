package com.epam.service;


import com.epam.dao.UsersDao;
import com.epam.dto.UserDto;
import com.epam.model.Role;
import com.epam.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UsersDao userDao;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UsersDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void signUp(UserDto user) {
        user.setSubscription("");
        user.setRole(Role.USER);
        User user1 = User.builder().build();
        modelMapper.map(user , user1);
        userDao.save(user1);
    }

    @Override
    public UserDto signIn(UserDto user) {
        User user1 = userDao.findByEmail(user.getEmail());
        modelMapper.map(user1, user);
        return user;
    }

    @Override
    public void subscribe(UserDto user) {
        String secretWord = "secret";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(secretWord.getBytes());
        String subscriptionKey = Arrays.toString(md.digest());
        if (user.getSubscription().equals(subscriptionKey)){
            System.out.println("Already subscribed");
            return;
        }
        user.setSubscription(subscriptionKey);
        User user1 = User.builder().build();
        modelMapper.map(user, user1);
        userDao.update(user1.getId(), user1.getSubscription());
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            UserDto userDto = UserDto.builder().build();
            modelMapper.map(user, userDto);
            userDtoList.add(userDto);
        }
        return  userDtoList;
    }
}
