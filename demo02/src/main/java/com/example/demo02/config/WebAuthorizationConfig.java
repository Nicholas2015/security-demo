package com.example.demo02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Nicholas Sun
 * @date 2022/10/09 22:52
 */
@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
//        http.authorizeRequests().anyRequest().hasAuthority("WRITE");
//        http.authorizeRequests().anyRequest().hasAnyAuthority("WRITE","READ");
//        http.authorizeRequests().anyRequest().access("hasAuthority('WRITE')");
//        String expression = "hasAuthority('read') and !hasAuthority('delete')";
//        http.authorizeRequests().anyRequest().access(expression);
//        http.authorizeRequests().anyRequest().hasRole("ADMIN");
        http.authorizeRequests().anyRequest().denyAll();
    }
}
