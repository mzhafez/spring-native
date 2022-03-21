package com.example.multimodules.core;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * Custom Authentication Provider. This checks whether we're authenticated or not
 */
@Component
public class SampleAuthenticationProvider implements AuthenticationProvider
{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        if (authentication != null && authentication.getPrincipal() != null)
        {
            authentication.setAuthenticated(true);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(AuthToken.class);
    }
}
