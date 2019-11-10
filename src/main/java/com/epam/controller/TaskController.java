package com.epam.controller;

import com.epam.dto.TaskDto;
import com.epam.dto.UserDto;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import com.epam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody UserDto user, TaskDto task) {
        taskService.createTask(user, task);
    }

    @DeleteMapping("/{taskId}/deleteTask")
    public void deleteTask(@PathVariable Long taskId, TaskDto task) {
        taskService.deleteTask(task);
    }

    @GetMapping("/getAllTasks")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTask();
    }

    @PutMapping("/{taskId}/setDone")
    public void setDone(@PathVariable Long taskId, TaskDto task) {
        taskService.setDone(task);
    }

    @PutMapping("/{taskId}/setUndone")
    public void setUndone(@PathVariable Long taskId, TaskDto task) {
        taskService.setUndone(task);
    }

    @PutMapping("/setPriority/{taskPriority}")
    public void setPriority(@PathVariable TaskDto task, TaskPriority taskPriority) {
        taskService.setPriority(task, taskPriority);
    }

    @PutMapping("/attachFile/{taskId}")
    public void attachFile(User user, @PathVariable Task task, File file) {
        taskService.attachFile(user, task, file);
    }

}
