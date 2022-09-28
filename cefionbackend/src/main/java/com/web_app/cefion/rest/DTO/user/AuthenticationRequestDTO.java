package com.web_app.cefion.rest.DTO.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDTO {
    @NotNull
    @NotEmpty
    private String username;
    @NotEmpty
    @NotNull
    private String password;
}