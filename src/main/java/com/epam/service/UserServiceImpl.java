package com.epam.service;

import com.epam.dao.UsersDao;
import com.epam.model.Task;
import com.epam.model.User;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UsersDao userDao = new UsersDao();

    @Override
    public void signUp(User user) {
        boolean done = userDao.save(user);
        if (done) {
            System.out.println("User is registered");
        } else {
            System.out.println("Can't register user");
        }
    }

    @Override
    public Optional<User> signIn(User user) {
        return userDao.findById(user.getId());
    }

    @Override
    public void subscribe(String email) {
        Optional optional = userDao.findByEmail(email);
        User user =(User) optional.get();
        String secretWord = "secret";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(secretWord.getBytes());
        String subscriptionKey = md.digest().toString();
        if (user.getSubscription().equals(subscriptionKey)){
            System.out.println("Already subscribed");
            return;
        }
        user.setSubscription(subscriptionKey);
        userDao.update(user.getId(), user);
    }
}
