package com.epam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String subscription;
    private Role role;

}
