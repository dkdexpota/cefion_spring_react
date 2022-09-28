package com.web_app.cefion.rest.DTO.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class RegistrationRequestDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$")
    private String username;
    @NotNull
    @NotEmpty
    private String tagName;
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,32}$")
    private String password;
    @NotNull
    @NotEmpty
    private List<String> roles;
}
