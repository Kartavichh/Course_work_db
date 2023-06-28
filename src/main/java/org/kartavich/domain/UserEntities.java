package org.kartavich.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity @Table
public class UserEntities implements UserDetails {
    @Id
    @GeneratedValue
    public Integer ID;
    public String username;
    public String password;
    @ManyToMany
    public Set<RolesEntities> roles;

    public UserEntities(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntities() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
