package com.example.securitydemo.config;

import com.example.securitydemo.model.SimpleUser;
import com.example.securitydemo.provider.CustomAuthenticationProvider;
import com.example.securitydemo.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author Nicholas Sun
 * @date 2022/09/24 22:06
 */
//@Configuration
public class GlobalConfig /*extends WebSecurityConfigurerAdapter*/ {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("nicholas").password("123456").authorities("read").build();
//        userDetailsService.createUser(user);
//        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    @Resource
    private CustomAuthenticationProvider authenticationProvider;

 /*   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().authenticated();
    }*/

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
//        var userDetailsService = new InMemoryUserDetailsManager();
//        var user = User.withUsername("nicholas").password("123456").authorities("read").build();
//        userDetailsService.createUser(user);
//        return userDetailsService;
//        SimpleUser user = new SimpleUser("nicholas", "123456", "read");
//        List<UserDetails> users = List.of(user);
//        return new InMemoryUserDetailsService(users);

//        String usersByUsernameQuery = "SELECT username,password,enabled FROM users WHERE username = ?";
//        String authsByUserQuery = "SELECT username,authority FROM authorities WHERE username = ?";
//
//        var userDetailsService = new JdbcUserDetailsManager(dataSource);
//        userDetailsService.setUsersByUsernameQuery(usersByUsernameQuery);
//        userDetailsService.setAuthoritiesByUsernameQuery(authsByUserQuery);
//        return userDetailsService;

        var cs = new DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
        cs.afterPropertiesSet();

        var manager = new LdapUserDetailsManager(cs);
        manager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups","uid"));
        manager.setGroupSearchBase("ou=groups");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
