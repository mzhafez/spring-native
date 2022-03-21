package com.example.multimodules.core;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SamplePrincipal implements UserDetails
{
    private final String username;

    public SamplePrincipal(String username)
    {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.emptyList();
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        return false;
    }
}
