package com.epam.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.File;

@Builder
@Data
@Entity
@Table(name = "TASK")
public class Task {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @NotEmpty
     private long id;

     @Column
     private String name;

     @Column
     private boolean isDone;

     @Column
     @ManyToOne
     @JoinColumn(name = "id", nullable = false)
     private User user;

     @Column
     @Enumerated(EnumType.STRING)
     private TaskPriority taskPriority;

     @Column
     private MultipartFile file;
}
