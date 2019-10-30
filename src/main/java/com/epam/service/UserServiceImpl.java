package com.epam.service;


import com.epam.dao.UsersDao;
import com.epam.exception.NoRightsForActionException;
import com.epam.exception.UserAlreadyExistsException;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.epam.rolesService.RolesService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UsersDao userDao;
     private RolesService roleService;
    private User userSignIn;

    @Autowired
    public UserServiceImpl(UsersDao userDao, RolesService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public void signUp(User user) {
        user.setSubscription("");
        if(!userDao.save(user)){
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public User signIn(User user) {
        return userDao.findById(user.getId());
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

    public void deleteUser(User user){
        if(roleService.canAccess(userSignIn.getRole().toString())){
            userDao.deleteById(user.getId());
        }
        else{
            throw new NoRightsForActionException();
        }
    }

}
