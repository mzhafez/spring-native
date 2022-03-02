package com.example.multimodules.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        var token = request.getHeader("Authorization");
        if (null != token)
        {
            SecurityContextHolder.getContext()
                    .setAuthentication(new AuthToken(List.of(new SimpleGrantedAuthority("ROLE_Admin")),
                            new SamplePrincipal("my user")));
        }
        filterChain.doFilter(request, response);
    }
}
