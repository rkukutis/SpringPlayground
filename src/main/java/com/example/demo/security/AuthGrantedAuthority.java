package com.example.demo.security;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Entity
public class AuthGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String authority;

    @Getter
    @ManyToOne
    @JoinColumn(name = "auth_user_detail_id")
    private AuthUserDetails authUserDetails;

    public AuthGrantedAuthority() {
    }

    public AuthGrantedAuthority(Long id, String authority, AuthUserDetails authUserDetails) {
        this.id = id;
        this.authority = authority;
        this.authUserDetails = authUserDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setAuthUserDetails(AuthUserDetails authUserDetails) {
        this.authUserDetails = authUserDetails;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
