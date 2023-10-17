package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.security.AuthUserDetails;

import java.util.Optional;

@Repository
public interface AuthUserDetailsRepository extends JpaRepository<AuthUserDetails, Long> {

    Optional<AuthUserDetails> findByUsername(String username);
    Optional<AuthUserDetails> findByPassword(String password);

}
