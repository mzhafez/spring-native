package com.example.multimodules.core;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



/**
 * Security configuration
 */
@Configuration
@ComponentScan (basePackages = "com.example")
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final String ALLOWED_ORIGIN_PATTERN = "*.mydomain.com:[*]";
    private static final String ANT_PATTERN = "/**";
    private SampleAuthenticationProvider authProvider;
    private final AuthFilter authFilter;

    @Autowired
    public SecurityConfig(SampleAuthenticationProvider authProvider, AuthFilter authFilter)
    {
        this.authProvider = authProvider;
        this.authFilter = authFilter;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .disable();
        http.csrf()
                .disable();
        http.antMatcher(ANT_PATTERN)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(corsFilter(), FilterSecurityInterceptor.class);
        http.addFilterBefore(authFilter, FilterSecurityInterceptor.class);
    }

    /**
     * CORS Filter
     *
     * @return cors filter
     */
    @Bean
    public CorsFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.TRUE);
        config.setAllowedOriginPatterns(List.of(ALLOWED_ORIGIN_PATTERN));
        config.setAllowedHeaders(
                Arrays.asList(HttpHeaders.ORIGIN, HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT, HttpHeaders.AUTHORIZATION, HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                        HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL,
                        "X-Requested-With", "Accept-Version", "Content-MD5", "CSRF-Token"));
        config.setExposedHeaders(
                Arrays.asList(HttpHeaders.ORIGIN, HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT, HttpHeaders.AUTHORIZATION, HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                        HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS));
        config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), HttpMethod.HEAD.name()));
        //GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(ANT_PATTERN, config);
        return new CorsFilter(source);
    }

    @Override
    public String toString()
    {
        return "com.example.multimodules.core.SecurityConfig{}";
    }
}
