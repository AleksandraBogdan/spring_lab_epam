package com.epam.dto;

import com.epam.model.TaskPriority;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

@Builder
@Data
public class TaskDto {
    private long id;
    @NotNull
    private String name;
    private boolean isDone;
    @NotEmpty
    private long userId;
    private TaskPriority taskPriority;
    private MultipartFile file;
}
