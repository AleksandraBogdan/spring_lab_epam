package com.epam.dao;

import com.epam.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksDao implements Dao<Task> {
    private static List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(Task.builder().id(1).name("wake up").isDone(false).userId(1).build());
        tasks.add(Task.builder().id(2).name("breakfast").isDone(false).userId(1).build());
    }

    @Override
    public void save(Task item) {
        tasks.add(item);
    }

    @Override
    public Task findById(long id) {
        return tasks.get((int) id);
    }

    @Override
    public void update(long id, Task item) {
        tasks.get((int) id).setId(item.getId());
        tasks.get((int) id).setName(item.getName());
        tasks.get((int) id).setDone(item.isDone());
        tasks.get((int) id).setUserId(item.getUserId());
    }


    @Override
    public void deleteById(long id) {
        tasks.remove(id);
    }

    public List<Task> getAllTask() {
        return tasks;
    }

    public void setDoneTask(Task task){
        tasks.get((int) task.getId()).setDone(true);
    }

    public void setUndoneTask(Task task) {
        tasks.get((int) task.getId()).setDone(false);
    }

}
