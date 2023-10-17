package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import security.AuthLoggingFilter;
import security.RequestValidationFilter;

@Configuration
public class ProjectConfig {

    // Adding a custom filter before default that checks for a specific request header
    // Also add filter with logger after basic auth filter

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthLoggingFilter(), BasicAuthenticationFilter.class)
            .authorizeRequests(c-> c.anyRequest().permitAll());
    return httpSecurity.build();
}



}
