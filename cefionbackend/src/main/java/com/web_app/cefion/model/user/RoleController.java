package com.web_app.cefion.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import java.util.stream.Collectors;

public abstract class RoleController {

    public static Set<SimpleGrantedAuthority> getAuthorities(Integer role) {
        Set<Role> roles = getRoles(role);
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        for(Set<SimpleGrantedAuthority> sga: roles.stream().map(Role::getAuthorities).toList())
            simpleGrantedAuthorities.addAll(sga);
        return simpleGrantedAuthorities;
    }

    public static Set<Role> getRoles(Integer role) {
        List<Boolean> perms = new ArrayList<>(Arrays.stream(Integer.toBinaryString(role).split("")).map(r -> r.equals("1")).toList());
        Collections.reverse(perms);
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < perms.size(); i++)
            if (perms.get(i)) roles.add(Role.values()[4 - i]);
        return roles;
    }

    public static Integer setAuthorities(List<String> roles) {
        List<Role> perms = roles.stream().map(String::toUpperCase).map(Role::valueOf).toList();
        int role = 0;
        for (int i = 0; i < 5; i++)
            if (perms.contains(Role.values()[4 - i])) role += 1 << i;
        return role;
    }
}
