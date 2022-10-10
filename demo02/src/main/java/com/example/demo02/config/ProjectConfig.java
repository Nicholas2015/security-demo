package com.example.demo02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Nicholas Sun
 * @date 2022/10/09 22:49
 */
@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails user1 = User.withUsername("john").password("123456").authorities("READ").build();
//        UserDetails user2 = User.withUsername("jane").password("123456").authorities("WRITE").build();
//        UserDetails user1 = User.withUsername("john").password("123456").authorities("read").build();
//        UserDetails user2 = User.withUsername("jane").password("123456").authorities("write","read","delete").build();
//        UserDetails user1 = User.withUsername("john").password("123456").authorities("ROLE_ADMIN").build();
//        UserDetails user2 = User.withUsername("jane").password("123456").authorities("ROLE_MANAGER").build();
        UserDetails user1 = User.withUsername("john").password("123456").roles("ADMIN").build();
        UserDetails user2 = User.withUsername("jane").password("123456").roles("MANAGER").build();
        manager.createUser(user1);
        manager.createUser(user2);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
