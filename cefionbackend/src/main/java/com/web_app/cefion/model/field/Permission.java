package com.web_app.cefion.model.field;

public enum Permission {
    MANAGE_USERS("manage:users"),
    MANAGE_POSTS("manage:posts"),
    STANDARD_ACCESS("standard:access");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
