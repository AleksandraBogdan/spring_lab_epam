package com.epam.service;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface TaskService {
    void createTask(User user, Task task);

    void deleteTask(Long taskId);

    List<Task> getAllTask();

    void setDone(Long taskId);

    void setUndone(Long taskId);

    void setPriority(Long taskId, TaskPriority taskPriority);

    void attachFile(User user, Long taskId, MultipartFile file);
}
