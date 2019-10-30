package com.epam.service;

import com.epam.dao.TasksDao;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao;

    @Autowired
    TaskServiceImpl(TasksDao tasksDao){
        this.tasksDao = tasksDao;
    }

    public void createTask(User user, Task task) {
        task.setUserId(user.getId());
        tasksDao.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        tasksDao.deleteById(task.getId());
    }

    @Override
    public List<Task> getAllTask() {
        return tasksDao.findAll();
    }

    @Override
    public void setDone(Task task) {
        tasksDao.setDoneTask(task.getId());
    }

    @Override
    public void setUndone(Task task) {
        tasksDao.setUndoneTask(task.getId());
    }

    public void setPriority(Task task, TaskPriority taskPriority) {
        tasksDao.setPriority(task.getId(), taskPriority);
    }
}
