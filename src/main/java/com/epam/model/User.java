package com.epam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
