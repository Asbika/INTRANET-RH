package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.entities.Role;
import com.giantLink.Hiring.recrutementservice.entities.User;
import com.giantLink.Hiring.recrutementservice.repositories.PermissionRepository;
import com.giantLink.Hiring.recrutementservice.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;
    Long id;
    String cin;
    String password;
    Role role;

    public UserDetailsImpl(Long id, String cin, String password, Role role){
        this.id = id;
        this.cin = cin;
        this.password = password;
        this.role = role;
    }
    public UserDetailsImpl(User user){
        id = user.getId();
        cin = user.getUsername();
        password = user.getPassword();
        role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        role.getPermissions().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.getLabel().toString())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cin;
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
