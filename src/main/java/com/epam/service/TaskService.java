package com.epam.service;

import com.epam.dto.TaskDto;
import com.epam.dto.UserDto;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface TaskService {
    void createTask(UserDto user, TaskDto task);

    void deleteTask(Long taskId);

    List<TaskDto> getAllTask();

    void setDone(Long taskId);

    void setUndone(Long taskId);

    void setPriority(Long taskId, TaskPriority taskPriority);

    void attachFile(UserDto user, Long taskId, MultipartFile file);
}
