package com.epam.service;

import com.epam.dto.TaskDto;
import com.epam.dto.UserDto;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;

import java.io.File;
import java.util.List;

public interface TaskService {
    void createTask(UserDto user, TaskDto task);

    void deleteTask(TaskDto task);

    List<TaskDto> getAllTask();

    void setDone(TaskDto task);

    void setUndone(TaskDto task);

    void setPriority(TaskDto task, TaskPriority taskPriority);

    void attachFile(UserDto user, TaskDto task, File file);
}
