package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.entities.User;
import com.giantLink.Hiring.recrutementservice.exceptions.LoginFailedException;
import com.giantLink.Hiring.recrutementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LoginFailedException());
        return new UserDetailsImpl(user);
    }
}
