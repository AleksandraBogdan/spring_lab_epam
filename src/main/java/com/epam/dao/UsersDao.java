package com.epam.dao;

import com.epam.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDao implements Dao<User> {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(User.builder().id(1).name("Alex").surname("Smith").email("alex@mail.com")
                .password("1234").build());
    }

    @Override
    public void save(User item) {
        users.add(item);
    }

    @Override
    public User findById(long id) {
        return users.get((int) id);
    }

    public List<User> findAll() {
        return users;
    }

    @Override
    public void update(long id, User item) {
        users.get((int) id).setId(item.getId());
        users.get((int) id).setName(item.getName());
        users.get((int) id).setSurname(item.getSurname());
        users.get((int) id).setEmail(item.getEmail());
        users.get((int) id).setPassword(item.getPassword());
    }

    @Override
    public void deleteById(long id) {
        users.remove(id);
    }


}
