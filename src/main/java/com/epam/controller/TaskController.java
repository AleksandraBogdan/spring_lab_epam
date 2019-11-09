package com.epam.controller;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import com.epam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public void createTask(@RequestBody User user, @RequestParam Task task) {
        taskService.createTask(user, task);
    }

    @DeleteMapping("/{taskId}/deleteTask")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    @PutMapping("/{taskId}/setDone")
    public void setDone(@PathVariable Long taskId) {
        taskService.setDone(taskId);
    }

    @PutMapping("/{taskId}/setUndone")
    public void setUndone(@PathVariable Long taskId) {
        taskService.setUndone(taskId);
    }

    @PutMapping("/setPriority/{taskPriority}")
    public void setPriority(@PathVariable Long taskId, TaskPriority taskPriority) {
        taskService.setPriority(taskId, taskPriority);
    }

    @PutMapping("/attachFile/{taskId}")
    public void attachFile(User user, @PathVariable Long taskId,@RequestParam MultipartFile file) {
        taskService.attachFile(user, taskId, file);
    }

}
