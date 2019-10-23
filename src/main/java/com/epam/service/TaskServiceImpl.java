package com.epam.service;

import com.epam.dao.TasksDao;
import com.epam.dao.UsersDao;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import com.epam.model.User;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao = new TasksDao();

    @Autowired
    UsersDao usersDao;
    public void createTask(User user, Task task) {
        Optional optional = usersDao.findById(user.getId());
        User curUser = (User) optional.get();
        curUser.getUserTasks().add(task);
        usersDao.update(curUser.getId(),curUser);
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

    public void setPriority(Task task, TaskPriority taskPriority) {
        tasksDao.setPriority(task.getId(), taskPriority);
    }

}
