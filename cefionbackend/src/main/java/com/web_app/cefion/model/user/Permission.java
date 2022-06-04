package com.web_app.cefion.model.user;

public enum Permission {
    MANAGE_USERS("manage:users"),
    EDIT_POSTS("edit:posts"),
    COPY_WRITE_POSTS("copy_write:posts"),
    STANDARD_ACCESS("standard:access");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
