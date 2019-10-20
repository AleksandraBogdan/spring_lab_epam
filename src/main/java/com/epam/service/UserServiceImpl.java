package com.epam.service;

import com.epam.dao.UsersDao;
import com.epam.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UsersDao userDao = new UsersDao();

    @Override
    public User signUp(User user) {
        if (!userDao.findAll().contains(userDao.findById(user.getId()))) {
            userDao.save(user);
            return user;
        } else
            return null;
    }

    @Override
    public User signIn(User user) {
        if (!userDao.findAll().contains(userDao.findById(user.getId()))) {
            userDao.findById(user.getId());
            return user;
        } else
            return null;
    }
}
