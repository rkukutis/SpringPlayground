package com.example.demo.security;

import com.example.demo.services.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    // Adding a custom filter before default that checks for a specific request header
    // Also add filter with logger after basic auth filter

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthLoggingFilter(), BasicAuthenticationFilter.class);

        // only user with admin authorization can access the teachers,
        // but anyone that is authenticated can access (GET) the students.
        // Other student operations reserved for admin
        httpSecurity.authorizeHttpRequests(c-> c.requestMatchers("api/v1/teacher").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "api/v1/student").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/v1/student").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "api/v1/student").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "api/v1/student").authenticated()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                .permitAll()
        ).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public DaoAuthenticationProvider jpaDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsManager);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(jpaDaoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

}
