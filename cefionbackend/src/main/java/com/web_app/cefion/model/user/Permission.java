package com.web_app.cefion.model.user;

import java.util.Set;

public enum Permission {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    EDITOR("editor"),
    COPYWRITER("copywriter"),
    USER("user");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
