package com.example.demo0601.config;

import com.example.demo0601.security.InitialAuthenticationFilter;
import com.example.demo0601.security.JwtAuthenticationFilter;
import com.example.demo0601.security.OtpAutenticationProvider;
import com.example.demo0601.security.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author sunchunyang
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private InitialAuthenticationFilter initialAuthenticationFilter;

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private OtpAutenticationProvider otpAutenticationProvider;

    @Resource
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 将这两个身份严重提供程序配置到身份验证管理器
        auth.authenticationProvider(otpAutenticationProvider)
                .authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用CSRF防护，在使用不同的源时并不适用
        http.csrf().disable();

        // 将这两个自定义过滤其添加到过滤器链中
        http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

        // 确保所有的请求都经过身份验证
        http.authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 将AuthenticationManager添加到Spring上下文，以便可以从过滤器类自动装配它
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
