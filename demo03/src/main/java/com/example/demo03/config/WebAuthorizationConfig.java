package com.example.demo03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests()
                .mvcMatchers("/hello").hasRole("ADMIN")
                .mvcMatchers("/ciao").hasRole("MANAGER")
                // 允许不登录访问接口
//                .anyRequest().permitAll();
                // 设置只有认证登录的用户可以访问
                .anyRequest().authenticated();
    }
}
