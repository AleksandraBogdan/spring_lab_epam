package com.epam.controller;

import com.epam.model.Task;
import com.epam.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskContoller {
    private TaskService taskService;

    private void createTask(Task task){
        taskService.createTask(task);
    }

    private void deleteTask(Task task){
        taskService.deleteTask(task);
    }

    private List<Task> getAllTasks(){
        return taskService.getAllTask();
    }

    private void setDone(Task task){
        taskService.setDone(task);
    }

    private void setUndone(Task task){
        taskService.setUndone(task);
    }


}
