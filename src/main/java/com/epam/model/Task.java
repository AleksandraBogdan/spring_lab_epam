package com.epam.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {
     private int id;
     private String name;
     private boolean isDone;
     private int userId;
}
