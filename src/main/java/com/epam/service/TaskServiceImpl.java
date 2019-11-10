package com.epam.service;

import com.epam.dao.TasksDao;
import com.epam.dto.TaskDto;
import com.epam.dto.UserDto;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao;
    private ModelMapper modelMapper;

    @Autowired
    TaskServiceImpl(TasksDao tasksDao, ModelMapper modelMapper){
        this.tasksDao = tasksDao;
        this.modelMapper = modelMapper;
    }

    public void createTask(UserDto user, TaskDto task) {
        task.setUserId(user.getId());
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }

    @Override
    public void deleteTask(TaskDto task) {
        tasksDao.deleteById(task.getId());
    }

    @Override
    public List<TaskDto> getAllTask() {
        List<Task> taskList = tasksDao.findAll(Sort.by(Sort.Order.asc("taskPriority")));
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task: taskList) {
            TaskDto taskDto = TaskDto.builder().build();
            modelMapper.map(task, taskDto);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public void setDone(TaskDto task) {
        task.setDone(true);
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }

    @Override
    public void setUndone(TaskDto task) {
        task.setDone(false);
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }

    public void setPriority(TaskDto task, TaskPriority taskPriority) {
        task.setTaskPriority(taskPriority);
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }

    public void attachFile(UserDto user, TaskDto task, File file) {
        task.setFile(file);
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }
}
