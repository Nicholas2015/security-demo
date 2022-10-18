package com.example.demo05.config;

import com.example.demo05.repo.CustomCsrfTokenRepository;
import com.example.demo05.security.CsrfTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nicholas Sun
 * @date 2022/10/16 16:36
 */
@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public CsrfTokenRepository csrfTokenRepository(){
        return new CustomCsrfTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class)
//                .authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin().defaultSuccessUrl("/main",true);

//        http.csrf(c -> c.ignoringAntMatchers("/ciao"));
//        http.csrf(c-> {
//            HandlerMappingIntrospector introspector = new HandlerMappingIntrospector();
//            MvcRequestMatcher matcher = new MvcRequestMatcher(introspector,"/ciao");
//            c.ignoringRequestMatchers(matcher);
//        });
//        http.csrf(c->{
//           c.csrfTokenRepository(csrfTokenRepository());
//           c.ignoringAntMatchers("/ciao");
//        });

//        http.csrf(c-> {
//            String pattern = ".*[0-9].*";
//            String name = HttpMethod.POST.name();
//            RegexRequestMatcher matcher = new RegexRequestMatcher(pattern, name);
//            c.ignoringRequestMatchers(matcher);
//        });
        http.cors(c->{
            CorsConfigurationSource source = new CorsConfigSource();
            c.configurationSource(source);
        });
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
    }
}
