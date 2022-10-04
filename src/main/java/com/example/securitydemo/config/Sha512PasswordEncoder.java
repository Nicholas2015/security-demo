package com.example.securitydemo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class Sha512PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return hashWithSHA512(rawPassword.toString());
    }

    private String hashWithSHA512(String rawPassword) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(rawPassword.getBytes());
            for (int i = 0; i < digest.length; i++) {
                result.append(Integer.toHexString(0xFF&digest[i]));
            }
        }catch (Exception e) {
            throw new RuntimeException("Bad Algorithm");
        }
        return result.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword.toString());
        return encodedPassword.equals(hashedPassword);
    }
}
