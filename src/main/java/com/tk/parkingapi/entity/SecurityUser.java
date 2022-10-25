package com.tk.parkingapi.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user
                        .getAuthority()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
}
