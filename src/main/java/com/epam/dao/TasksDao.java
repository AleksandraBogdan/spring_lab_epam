package com.epam.dao;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class TasksDao implements Dao<Task> {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Task> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return Task.builder().id(resultSet.getLong("id")).name(resultSet.getString("name")).
                isDone(resultSet.getBoolean("isDone")).userId(resultSet.getLong("userId")).
                taskPriority(TaskPriority.valueOf(resultSet.getString("taskPriority"))).build();
    };

    @Autowired
    public TasksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Task item) {
        jdbcTemplate.update("insert into tasks values (?, ?, ?, ?, ?)", item.getId(), item.getName(),
                item.isDone(), item.getUserId(), item.getTaskPriority());

    }

    @Override
    public Task findById(long id) {
        Task task = null;
        try {
            task = jdbcTemplate.queryForObject("select * from tasks where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
        return task;
    }


    @Override
    public void update(long id, Task item) {
        jdbcTemplate.update("update tasks set name = ?2, done = ?3, user_id = ?4, priority = ?5 where id = ?1", item.getId(), item.getName(),
                item.isDone(), item.getUserId(), item.getTaskPriority());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("delete from tasks where id = ?", id);

    }

    public List<Task> findAll() {
        return jdbcTemplate.query("select * from tasks", ROW_MAPPER);
    }

    public void setDoneTask(long id) {
        jdbcTemplate.update("update tasks set done = true where id = ?1", id);
    }

    public void setUndoneTask(long id) {
        jdbcTemplate.update("update tasks set done = false where id = ?1", id);
    }

    public void setPriority(long id, TaskPriority taskPriority) {
        jdbcTemplate.update("update tasks set priority = ?2 where id = ?1", id, taskPriority.toString());
    }

    public void attachFile(long id, MultipartFile file) {
        jdbcTemplate.update("update tasks set file = ?2 where id = ?1", id, file);
    }

}
