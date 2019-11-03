package com.epam.controller;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import com.epam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody User user, @RequestBody Task task) {
        taskService.createTask(user, task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.deleteTask(task);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    @PutMapping("/setDone/{id}")
    public void setDone(@PathVariable Long id, @RequestBody Task task) {
        taskService.setDone(task);
    }

    @PutMapping("/setUndone/{id}")
    public void setUndone(@PathVariable Long id, @RequestBody Task task) {
        taskService.setUndone(task);
    }

    @PutMapping("/setPriority/{taskPriority}")
    public void setPriority(@PathVariable Task task, TaskPriority taskPriority) {
        taskService.setPriority(task, taskPriority);
    }
}
