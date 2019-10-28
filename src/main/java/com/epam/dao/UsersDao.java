package com.epam.dao;

import com.epam.exception.NoSuchUserException;
import com.epam.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsersDao implements Dao<User> {
    private static Set<User> users = new HashSet<>();

    static {
        users.add(User.builder().id(1).name("Alex").surname("Smith").email("alex@mail.com")
                .password("1234").userTasks(new ArrayList<>()).build());
    }

    @Override
    public boolean save(User item) {
        Optional<Integer> maxUserId = users.stream()
                .map(User::getId)
                .max(Collections.reverseOrder());
        item.setId(maxUserId.orElse(0) + 1);
        return users.add(item);
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny();
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    public Set<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> update(int id, User item) {
        return users.stream().filter(user -> user.getId() == id).peek(user -> {
            user.setPassword(item.getPassword());
            user.setEmail(item.getEmail());
            user.setSurname(item.getSurname());
            user.setName(item.getName());
            user.setUserTasks(item.getUserTasks());
            user.setSubscription(item.getSubscription());
        }).findAny();
    }

    @Override
    public void deleteById(int id) {
        Optional<User> userOnDelete = users.stream().filter(user -> user.getId() == id).findAny();
        users.remove(userOnDelete.orElseThrow(NoSuchUserException::new));
    }
}
