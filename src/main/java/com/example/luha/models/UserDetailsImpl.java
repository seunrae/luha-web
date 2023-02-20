package com.example.luha.models;

import com.example.luha.ROLE;
import com.example.luha.models.User;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;
    private ROLE role;

    private Boolean enabled;
    private Boolean locked;

    public UserDetailsImpl(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getUserRole();
        this.enabled = user.getEnabled();
        this.locked = user.getLocked();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(String.valueOf(role)));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
