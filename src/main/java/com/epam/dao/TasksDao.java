package com.epam.dao;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

public interface TasksDao extends JpaRepository<Task, Integer> {
    @Override
    List<Task> findAll(Sort sort);
}
