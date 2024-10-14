package com.example.lookkit.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private long userId;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}