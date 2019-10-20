package com.epam.service;

import com.epam.model.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task);

    void deleteTask(Task task);

    List<Task> getAllTask();

    void setDone(Task task);

    void setUndone(Task task);
}
