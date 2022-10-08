package com.example.demoorigional.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Nicholas Sun
 * @date 2022/10/02 10:30
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
