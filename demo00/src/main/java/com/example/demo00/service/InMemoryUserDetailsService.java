package com.example.demo00.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Objects;

/**
 * @author Nicholas Sun
 * @date 2022/09/28 22:54
 */
public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(u -> Objects.equals(u.getUsername(),username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
