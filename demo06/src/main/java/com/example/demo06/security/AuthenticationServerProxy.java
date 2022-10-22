package com.example.demo06.security;

import com.example.demo06.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class AuthenticationServerProxy {

    @Resource
    private RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username,String password) {
        String url = baseUrl + "/user/auth";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user);
        restTemplate.postForEntity(url,entity, Void.class);
    }

    public boolean sendOtp(String username,String code) {
            String url = baseUrl + "/otp/check";
            User user = new User();
            user.setUsername(username);
            user.setCode(code);
            HttpEntity<User> entity = new HttpEntity<>(user);
            var response = restTemplate.postForEntity(url,entity, Void.class);
            return response.getStatusCode().equals(HttpStatus.OK);
    }
}
