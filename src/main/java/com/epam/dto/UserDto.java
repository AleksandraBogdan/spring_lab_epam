package com.epam.dto;

import com.epam.model.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserDto {
    private int id;
    @NotEmpty
    private String name;
    private String surname;
    @Email
    private String email;
    private String subscription;
    private Role role;
}
