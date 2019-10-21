package com.epam.service;

import com.epam.dao.TasksDao;
import com.epam.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao = new TasksDao();

    @Override
    public void createTask(Task task) {
        tasksDao.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        boolean done = tasksDao.deleteById(task.getId());
        if (done) {
            System.out.println("Successful deleting");
        } else {
            System.out.println("Can't delete task");
        }
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
}
