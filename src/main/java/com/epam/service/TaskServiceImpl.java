package com.epam.service;

import com.epam.aspect.SubscriptionAspect;
import com.epam.dao.TasksDao;

import com.epam.exception.SubscriptionException;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao;

    @Autowired
    TaskServiceImpl(TasksDao tasksDao){
        this.tasksDao = tasksDao;
    }

    private UsersDao usersDao = new UsersDao();

    public void createTask(User user, Task task) {
        task.setUserId(user.getId());
        tasksDao.save(task);
        user.getUserTasks().add(task);
        usersDao.update(user.getId(), user);
    }

    @Override
    public void deleteTask(Long taskId) {
        tasksDao.deleteById(taskId);
    }

    @Override
    public List<Task> getAllTask() {
        return tasksDao.findAll();
    }

    @Override
    public void setDone(Long taskId) {
        tasksDao.setDoneTask(taskId);
    }

    @Override
    public void setUndone(Long taskId) {
        tasksDao.setUndoneTask(taskId);
    }

    public void setPriority(Long taskId, TaskPriority taskPriority) {
        tasksDao.setPriority(taskId, taskPriority);
    }

    public void attachFile(User user, Long taskId, MultipartFile file) {
        if (!user.getSubscription().equals(SubscriptionAspect.getSecretWord())) {
            throw new SubscriptionException();
        }
        tasksDao.attachFile(taskId, file);
    }
}
