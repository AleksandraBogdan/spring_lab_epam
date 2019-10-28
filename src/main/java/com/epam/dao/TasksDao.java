package com.epam.dao;

import com.epam.exception.NoSuchTaskException;
import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TasksDao implements Dao<Task> {
    private List<Task> tasks = new ArrayList<>();

     {
        tasks.add(Task.builder().id(1).name("wake up").isDone(false).userId(1).build());
        tasks.add(Task.builder().id(2).name("breakfast").isDone(false).userId(1).build());
    }

    @Override
    public boolean save(Task item) {
        item.setId(tasks.size() + 1);
        return tasks.add(item);
    }

    @Override
    public Optional<Task> findById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findAny();
    }


    @Override
    public Optional<Task> update(int id, Task item) {
        return tasks.stream().filter(task -> task.getId() == id).peek(task -> {
            task.setName(item.getName());
            task.setDone(item.isDone());
            task.setUserId(item.getUserId());
        }).findAny();
    }

    @Override
    public void deleteById(int id) {
        Optional<Task> taskOnDelete = tasks.stream().filter(task -> task.getId() == id).findAny();
        tasks.remove(taskOnDelete.orElseThrow(NoSuchTaskException::new).getId() - 1);

    }

    public List<Task> findAll() {
        return tasks;
    }

    public void setDoneTask(int id) {
        tasks.stream().filter(task -> task.getId() == id).forEach(task -> task.setDone(true));
    }

    public void setUndoneTask(int id) {
        tasks.stream().filter(task -> task.getId() == id).forEach(task -> task.setDone(false));
    }

    public void setPriority(int id, TaskPriority taskPriority) {
        tasks.stream().filter(task -> task.getId() == id).forEach(task -> task.setTaskPriority(taskPriority));
    }
}
