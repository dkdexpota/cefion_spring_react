package com.web_app.cefion.service;

import com.web_app.cefion.model.user.User;

import java.util.regex.Pattern;

public class UserValidate {
    public static boolean validate(User user) {
//        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,32}$");
//        Pattern namePattern = Pattern.compile("^[A-Za-z]+$");
//        Pattern usernamePattern = Pattern.compile("^[:graph:]{4,32}$");
//        return validateString(user.getUsername(), usernamePattern) &&
//                validateString(user.getPassword(), passwordPattern) &&
//                validateString(user.getName(), namePattern) &&
//                validateString(user.getSurname(), namePattern);
        return true;
    }

    private static boolean validateString(String stringForValidate, Pattern pattern) {
        return pattern.matcher(stringForValidate).find();
    }
}
