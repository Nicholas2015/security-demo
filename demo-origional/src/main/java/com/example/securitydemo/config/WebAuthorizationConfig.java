package com.example.securitydemo.config;

import com.example.securitydemo.filter.CustomAuthenticationFailureHandler;
import com.example.securitydemo.filter.CustomAuthenticationSuccessHandler;
import com.example.securitydemo.filter.CustomEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author Nicholas Sun
 * @date 2022/09/26 22:44
 */
//@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic(c->{
        //    c.realmName("OTHER");
        //    c.authenticationEntryPoint(new CustomEntryPoint());
        //});
        //        http.httpBasic();
//        http.formLogin().defaultSuccessUrl("/home",true);
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
