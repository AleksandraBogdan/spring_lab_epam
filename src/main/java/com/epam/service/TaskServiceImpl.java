package com.epam.service;

import com.epam.aspect.SubscriptionAspect;
import com.epam.dao.TasksDao;

import com.epam.exception.SubscriptionException;

import com.epam.dto.TaskDto;
import com.epam.dto.UserDto;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TasksDao tasksDao;
    private ModelMapper modelMapper;

    @Autowired
    TaskServiceImpl(TasksDao tasksDao, ModelMapper modelMapper){
        this.tasksDao = tasksDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTask(UserDto user, TaskDto task) {
        task.setUserId(user.getId());
        Task task1 = Task.builder().build();
        modelMapper.map(task, task1);
        tasksDao.save(task1);
    }

    @Override
    public void deleteTask(Long taskId) {
        tasksDao.deleteById(taskId);
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
    public void setDone(Long taskId) {
        Optional<Task> task = tasksDao.findById(taskId);
        task.get().setDone(true);
        tasksDao.updateDone(taskId, task.get().isDone());
    }

    public void setUndone(Long taskId) {
        Optional<Task> task = tasksDao.findById(taskId);
        task.get().setDone(false);
        tasksDao.updateDone(taskId, task.get().isDone());
    }

    public void setPriority(Long taskId, TaskPriority taskPriority) {
        Optional<Task> task = tasksDao.findById(taskId);
        task.get().setTaskPriority(taskPriority);
        tasksDao.updatePriority(taskId, task.get().getTaskPriority().toString());
    }


    public void attachFile(UserDto user, Long taskId, MultipartFile file) {
        if (!user.getSubscription().equals(SubscriptionAspect.getSecretWord())) {
            throw new SubscriptionException();
        }
        Optional<Task> task = tasksDao.findById(taskId);
        task.get().setFile(file);
        tasksDao.updateFile(taskId, task.get().getFile());

    }
}
