package com.epam.dto;

import com.epam.model.TaskPriority;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

@Builder
@Data
public class TaskDto {
    private int id;
    @NotNull
    private String name;
    private boolean isDone;
    @NotEmpty
    private int userId;
    private TaskPriority taskPriority;
    private File file;
}
