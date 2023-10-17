package com.example.demo.services;

import com.example.demo.repositories.AuthUserDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import com.example.demo.security.AuthUserDetails;

@Service
public class CustomUserDetailsManager  implements UserDetailsManager {

    @Autowired
    private AuthUserDetailsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user with username " + username + " not found"));
    }

    public void createUser(UserDetails user) {
        repository.save((AuthUserDetails) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        repository.save((AuthUserDetails) user);

    }

    @Override
    public void deleteUser(String username) {
        var userDetails = repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User " + username + " does not exist"));
        repository.delete((userDetails));
    }

    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        AuthUserDetails userDetails = repository.findByPassword(oldPassword)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid password"));
        userDetails.setPassword(newPassword);
        repository.save(userDetails);

    }

    @Override
    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }
}
