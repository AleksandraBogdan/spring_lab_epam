package com.epam.service;

import com.epam.dao.UsersDao;
import com.epam.model.User;
import org.springframework.stereotype.Service;

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
            System.out.println("Cant register");
        }
    }

    @Override
    public Optional<User> signIn(User user) {
        return userDao.findById(user.getId());
    }
}
