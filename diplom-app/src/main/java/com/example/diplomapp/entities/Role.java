package com.example.diplomapp.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    WORKER,
    CHIEF;

    @Override
    public String getAuthority() { return name(); }
}
