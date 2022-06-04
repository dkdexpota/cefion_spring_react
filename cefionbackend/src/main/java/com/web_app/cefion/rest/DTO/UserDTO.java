package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.user.Role;
import com.web_app.cefion.model.user.Status;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private Role role;
    private Status status;
}
