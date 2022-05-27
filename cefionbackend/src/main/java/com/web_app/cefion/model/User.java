package com.web_app.cefion.model;

import com.web_app.cefion.model.field.Role;
import com.web_app.cefion.model.field.Status;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USERS_TB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD")
    private String password;

//    @Column(name = "NAME")
//    private String name;
//    @Column(name = "SURNAME")
//    private String surname;

    @Column(name = "ROLE")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
