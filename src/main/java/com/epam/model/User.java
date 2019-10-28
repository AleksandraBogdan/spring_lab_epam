package com.epam.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String subscription;
    private List<Task> userTasks;

    public List<Task> getUserTasks() {
        return userTasks;
    }
}
