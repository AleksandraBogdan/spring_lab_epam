package com.epam.service;

import com.epam.dao.TasksDao;
import com.epam.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private TasksDao tasksDao = new TasksDao();
    @Override
    public void createTask(Task task) {
        tasksDao.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        tasksDao.deleteById(task.getId());
    }

    @Override
    public List<Task> getAllTask() {
        return tasksDao.getAllTask();
    }

    @Override
    public void setDone(Task task) {
        tasksDao.setDoneTask(task);

    }

    @Override
    public void setUndone(Task task) {
        tasksDao.setUndoneTask(task);
    }
}
