package com.epam.controller;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import com.epam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskContoller {

    private TaskService taskService;

    @Autowired
    public TaskContoller(TaskService taskService) {
        this.taskService = taskService;
    }

    public void createTask(User user, Task task){
        taskService.createTask(user, task);
    }

    public void deleteTask(Task task){
        taskService.deleteTask(task);
    }

    public List<Task> getAllTasks(){
        return taskService.getAllTask();
    }

    public void setDone(Task task){
        taskService.setDone(task);
    }

    public void setUndone(Task task){
        taskService.setUndone(task);
    }

    public void setPriority(Task task, TaskPriority taskPriority) {
        taskService.setPriority(task, taskPriority);
    }

}
