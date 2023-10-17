package com.example.demo.config;

import com.example.demo.repositories.AuthGrantedAuthorityRepository;
import com.example.demo.repositories.AuthUserDetailsRepository;
import com.example.demo.security.AuthGrantedAuthority;
import com.example.demo.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class UserConfig {

    @Autowired
    private AuthUserDetailsRepository authUserDetailsRepository;
    @Autowired
    private AuthGrantedAuthorityRepository authGrantedAuthorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
     public CommandLineRunner addMockUsers() {
        return (args) -> {

            AuthUserDetails user2 = new AuthUserDetails();
            user2.setUsername("rhoopoe");
            user2.setPassword(passwordEncoder.encode("kukutis"));
            user2.setEnabled(true);
            user2.setCredentialsNonExpired(true);
            user2.setAccountNonExpired(true);
            user2.setAccountNonLocked(true);

            AuthGrantedAuthority grantedAuthority = new AuthGrantedAuthority();
            grantedAuthority.setAuthority("USER");
            grantedAuthority.setAuthUserDetails(user2);
            authUserDetailsRepository.save(user2);
            authGrantedAuthorityRepository.save(grantedAuthority);
            user2.setAuthorities(Collections.singleton(grantedAuthority));
        };
    }

}
