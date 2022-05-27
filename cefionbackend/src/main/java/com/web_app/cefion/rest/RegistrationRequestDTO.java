package com.web_app.cefion.rest;

import com.web_app.cefion.model.field.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationRequestDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$")
    private String username;
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,32}$")
    private String password;

    @NotNull
    @NotEmpty
    private Role role;
}
