package com.web_app.cefion.model.field;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    SUPER_ADMIN(Set.of(Permission.MANAGE_POSTS, Permission.MANAGE_USERS, Permission.STANDARD_ACCESS)),
    ADMIN(Set.of(Permission.MANAGE_POSTS, Permission.MANAGE_USERS, Permission.STANDARD_ACCESS)),
    EDITOR(Set.of(Permission.MANAGE_POSTS, Permission.STANDARD_ACCESS)),
    COPYWRITER(Set.of(Permission.MANAGE_POSTS, Permission.STANDARD_ACCESS)),
    USER(Set.of(Permission.STANDARD_ACCESS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
