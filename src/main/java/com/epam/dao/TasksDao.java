package com.epam.dao;

import com.epam.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TasksDao implements Dao<Task> {
    private static List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(Task.builder().id(1).name("wake up").isDone(false).userId(1).build());
        tasks.add(Task.builder().id(2).name("breakfast").isDone(false).userId(1).build());
    }

    @Override
    public boolean save(Task item) {
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
    public boolean deleteById(int id) {
        Optional<Task> taskOnDelete = tasks.stream().filter(task -> task.getId() == id).findAny();
        if (taskOnDelete.isPresent()) {
            return tasks.remove(taskOnDelete);
        } else {
            return false;
        }
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
}
