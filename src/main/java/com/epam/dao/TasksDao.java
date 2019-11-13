package com.epam.dao;

import com.epam.model.Task;
import com.epam.model.TaskPriority;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface TasksDao extends JpaRepository<Task, Long> {
    @Override
    List<Task> findAll(Sort sort);

    @Query("update TASK set done = :done where id = :id")
    void updateDone(@Param("id") long id, @Param("done") boolean isDone);

    @Query("update TASK set priority = :prior where id = :id")
    void updatePriority(@Param("id") long id, @Param("prior") String priority);
    @Query("update TASK set file = :file where id = :id")
    void updateFile(@Param("id") Long taskId, @Param("file") MultipartFile file);
}
