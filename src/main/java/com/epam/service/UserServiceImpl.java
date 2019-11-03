package com.epam.service;


import com.epam.dao.UsersDao;
import com.epam.model.Role;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UsersDao userDao;

    @Autowired
    public UserServiceImpl(UsersDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signUp(User user) {
        user.setSubscription("");
        user.setRole(Role.USER);
        userDao.save(user);//бросить exception
    }

    @Override
    public User signIn(User user) {
        return userDao.findByEmail(user.getEmail());
    }

    @Override
    public void subscribe(User user) {
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
        userDao.update(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
