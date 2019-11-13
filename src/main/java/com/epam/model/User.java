package com.epam.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@JsonDeserialize(builder = User.UserBuilder.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotEmpty
    private long id;

    @Column
    @NotEmpty
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    private String password;

    @Column
    private String subscription;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
