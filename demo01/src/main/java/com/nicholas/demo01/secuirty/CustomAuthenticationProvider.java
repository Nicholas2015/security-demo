package com.nicholas.demo01.secuirty;

import com.nicholas.demo01.model.CustomUserDetails;
import com.nicholas.demo01.service.impl.JpaUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Nicholas Sun
 * @date 2022/10/07 21:54
 */
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private JpaUserDetailsServiceImpl jpaUserDetailsService;

//    @Resource
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Resource
//    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(username);

        switch (user.getUser().getAlgorithm()) {
            case BCRYPT:
                  return checkPassword(user,password,passwordEncoder);
//                return checkPassword(user,password,bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(user,password,passwordEncoder);
//                return checkPassword(user,password,sCryptPasswordEncoder);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
        if (encoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
