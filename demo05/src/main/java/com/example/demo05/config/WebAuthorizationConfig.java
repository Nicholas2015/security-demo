package com.example.demo05.config;

import com.example.demo05.security.CsrfTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * @author Nicholas Sun
 * @date 2022/10/16 16:36
 */
@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class)
//                .authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().defaultSuccessUrl("/main",true);
    }
}
