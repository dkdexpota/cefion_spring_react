package com.web_app.cefion.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    SUPER_ADMIN(Set.of(Permission.SUPER_ADMIN, Permission.ADMIN, Permission.EDITOR, Permission.COPYWRITER, Permission.USER)),
    ADMIN(Set.of(Permission.ADMIN)),
    EDITOR(Set.of(Permission.EDITOR)),
    COPYWRITER(Set.of(Permission.COPYWRITER)),
    USER(Set.of(Permission.USER));

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
