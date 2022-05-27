package com.web_app.cefion.rest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$")
    private String username;
    @NotEmpty
    @NotNull
    private String password;
}