package com.epam.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Builder
@Data
public class Task {
     private long id;
     private String name;
     private boolean isDone;
     private long userId;
     private TaskPriority taskPriority;
     private MultipartFile file;
}
