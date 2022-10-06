package com.example.securitydemo.config;

import com.example.securitydemo.filter.CustomEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Nicholas Sun
 * @date 2022/09/26 22:44
 */
@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic(c->{
        //    c.realmName("OTHER");
        //    c.authenticationEntryPoint(new CustomEntryPoint());
        //});
        //        http.httpBasic();
        http.formLogin().defaultSuccessUrl("/home",true);
        http.authorizeRequests().anyRequest().authenticated();
    }
}
