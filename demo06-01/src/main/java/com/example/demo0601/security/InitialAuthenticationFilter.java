package com.example.demo0601.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        if (StringUtils.isBlank(code)) {

            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(authentication);
        } else {
            // 为OTP代码不为null的情况添加分支，本示例假定客户端发送一个OTP用于第二个身份验证步骤
            // 对第二个身份验证步骤，需要创建一个OtpAuthentication类型的实例，并将其发送到AuthenticationManager，后者会为其找到合适的提供程序
            Authentication authentication = new OtpAuthentication(username, code);
            authentication = manager.authenticate(authentication);
            SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            // 构建JWT并将已验证用户的用户名存储为其声明之一。这里使用的密钥对令牌进行签名
            String jwt = Jwts.builder().setClaims(Map.of("username", username))
                    .signWith(secretKey)
                    .compact();
            response.setHeader("Authorization", jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
