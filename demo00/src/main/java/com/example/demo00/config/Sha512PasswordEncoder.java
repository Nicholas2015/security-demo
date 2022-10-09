package com.example.demo00.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Nicholas Sun
 * @date 2022/10/02 10:31
 */
public class Sha512PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return hashWithSHA512(rawPassword.toString());
    }

    private String hashWithSHA512(String rawPassword) {
        StringBuilder builder = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(rawPassword.getBytes());
            for (int i = 0; i < digest.length; i++) {
                builder.append(0xFF & digest[i]);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Bad algorithm");
        }

        return builder.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword);
        return encodedPassword.equals(hashedPassword);
    }
}
