package com.example.multimodules.core;

import java.util.List;

import javax.security.auth.Subject;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthToken extends AbstractAuthenticationToken
{
    private final SamplePrincipal samplePrincipal;

    public AuthToken(List<SimpleGrantedAuthority> roles, SamplePrincipal samplePrincipal)
    {
        super(roles);
        this.samplePrincipal = samplePrincipal;
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }

    @Override
    public Object getPrincipal()
    {
        return null;
    }

    @Override
    public boolean implies(Subject subject)
    {
        return super.implies(subject);
    }
}
