package com.example.securitydemo.config;

import com.example.securitydemo.filter.CustomAuthenticationFailureHandler;
import com.example.securitydemo.filter.CustomAuthenticationProvider1;
import com.example.securitydemo.filter.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicholas Sun
 * @date 2022/09/24 22:06
 */
@Configuration
//@EnableAsync
public class GlobalConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("nicholas").password("123456").authorities("read").build();
//        userDetailsService.createUser(user);
//        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Resource
//    private CustomAuthenticationProvider1 authenticationProvider;

// @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(authenticationProvider);
//    }


//    @Bean
//    public InitializingBean initializingBean(){
//     return () -> SecurityContextHolder.setStrategyName(
////             SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
//             SecurityContextHolder.MODE_GLOBAL
//     );
//    }

    @Resource
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }


    private void testPasswordEncoder(){
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
        encoder = new StandardPasswordEncoder();
        encoder = new Pbkdf2PasswordEncoder();
        encoder = new Pbkdf2PasswordEncoder("secret");
        encoder = new Pbkdf2PasswordEncoder("secret",185000,256);
        encoder = new BCryptPasswordEncoder();
        encoder = new SCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain() {
//
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        var user = User.withUsername("nicholas").password("123456").authorities("read").build();
        userDetailsService.createUser(user);
        return userDetailsService;
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

//        var cs = new DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
//        cs.afterPropertiesSet();
//
//        var manager = new LdapUserDetailsManager(cs);
//        manager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups","uid"));
//        manager.setGroupSearchBase("ou=groups");
//        return manager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        Map<String,PasswordEncoder> encoder = new HashMap<>();
//        encoder.put("noop",NoOpPasswordEncoder.getInstance());
//        encoder.put("bcrypt",new BCryptPasswordEncoder());
//        encoder.put("scrypt",new SCryptPasswordEncoder());
//        return new DelegatingPasswordEncoder("bcrypt",encoder);
    }
}
