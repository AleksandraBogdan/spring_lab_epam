package com.epam.controller;

import com.epam.model.Task;
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

    public void createTask(Task task){
        taskService.createTask(task);
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


}
