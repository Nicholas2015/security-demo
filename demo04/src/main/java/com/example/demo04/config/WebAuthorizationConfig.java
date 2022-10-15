package com.example.demo04.config;

import com.example.demo04.security.AuthenticationLoggingFilter;
import com.example.demo04.security.RequestValidationFilter;
import com.example.demo04.security.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private StaticKeyAuthenticationFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(filter, BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();
    }
}
