package com.web_app.cefion.model.user;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(name = "TAG_NAME")
    private String tagName;

    @Column(name = "ROLE")
    private Integer role;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
