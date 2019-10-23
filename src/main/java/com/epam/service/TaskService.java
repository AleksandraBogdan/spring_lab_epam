package com.epam.service;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;

import java.util.List;

public interface TaskService {
    void createTask(User user, Task task);

    void deleteTask(Task task);

    List<Task> getAllTask();

    void setDone(Task task);

    void setUndone(Task task);

    void setPriority(Task task, TaskPriority taskPriority);
}
