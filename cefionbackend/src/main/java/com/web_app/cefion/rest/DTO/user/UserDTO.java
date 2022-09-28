package com.web_app.cefion.rest.DTO.user;

import com.web_app.cefion.model.user.Status;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String tagName;
    private List<String> roles;
    private Status status;
}
