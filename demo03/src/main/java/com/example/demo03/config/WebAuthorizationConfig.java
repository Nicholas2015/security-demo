package com.example.demo03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests()
//                .mvcMatchers("/hello").hasRole("ADMIN")
//                .mvcMatchers("/ciao").hasRole("MANAGER")
//                .mvcMatchers(HttpMethod.GET,"/a").authenticated()
//                .mvcMatchers(HttpMethod.POST,"/a").permitAll()
//                .mvcMatchers("/a/b/**").authenticated()
//                .anyRequest().denyAll();
                // 允许不登录访问接口
//                .anyRequest().permitAll();
                // 设置只有认证登录的用户可以访问
//                .anyRequest().authenticated();
//        .mvcMatchers("/product/{code:^[0-9]*$}")
//                .permitAll()
//                .anyRequest().denyAll();
//        http.csrf().disable();

//                .mvcMatchers("/hello")
//                .antMatchers("/hello")
//                .authenticated();
//                .mvcMatchers("/email/{mail:.*(.+@.+\\.com)}")
                .regexMatchers("/email/{mail:.*(.+@.+\\.com)}")
                .permitAll()
                .anyRequest().denyAll();
//                .regexMatchers(".*(us|uk|ca)+/(en|fr).*")
//                .authenticated()
//                .anyRequest().hasAnyAuthority("premium");
    }
}
