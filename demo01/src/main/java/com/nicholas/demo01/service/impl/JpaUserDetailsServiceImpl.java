package com.nicholas.demo01.service.impl;

import com.nicholas.demo01.model.CustomUserDetails;
import com.nicholas.demo01.model.User;
import com.nicholas.demo01.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.function.Supplier;

@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("Problem during authentication!");
        User user = userRepository.findUserByUsername(username).orElseThrow(supplier);
        return new CustomUserDetails(user);
    }
}
