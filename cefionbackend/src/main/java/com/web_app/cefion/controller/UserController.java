package com.web_app.cefion.controller;

import com.web_app.cefion.model.User;
import com.web_app.cefion.service.UserService;
import com.web_app.cefion.service.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        if (!UserValidate.validate(user)) {
            return "Validate error";
        }
        userService.saveUser(user);
        return "New user is added.";
    }
}
