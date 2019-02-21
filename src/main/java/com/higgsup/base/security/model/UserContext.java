package com.higgsup.base.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserContext {
    private final Long userId;
    private final String email;
    private final List<GrantedAuthority> authorities;

    private UserContext(Long userId, String email,
        List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.authorities = authorities;
    }
    
    public static UserContext create(Long userId, String email, List<GrantedAuthority> authorities) {
        if (StringUtils.isEmpty(email)) throw new IllegalArgumentException("Email is blank: " + email);
        return new UserContext(userId, email, authorities);
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
