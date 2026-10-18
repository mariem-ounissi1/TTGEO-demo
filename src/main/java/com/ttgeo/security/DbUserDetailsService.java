package com.ttgeo.security;

import com.ttgeo.model.AppUser;
import com.ttgeo.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private final UserRepository users;

    public DbUserDetailsService(UserRepository users) { this.users = users; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser u = users.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        return User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole()).build();
    }
}
