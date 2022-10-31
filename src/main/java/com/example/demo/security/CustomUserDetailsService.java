package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserDtailsDAO customUserDtailsDAO;

    @Autowired
    public CustomUserDetailsService(@Qualifier("customUserDtailsDAO") CustomUserDtailsDAO customUserDtailsDAO) {
        this.customUserDtailsDAO = customUserDtailsDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return this.customUserDtailsDAO.loadUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }
}
