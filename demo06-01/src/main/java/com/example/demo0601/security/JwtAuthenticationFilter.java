package com.example.demo0601.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * JWT也称为JW(JSON Web Token Signed)
 * @author sunchunyang
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        // 解析令牌一会去声明并验证签名。如果签名无效，则抛出异常
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        String username = String.valueOf(claims.get("username"));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");
        // 创建添加到SecuirtyContext的Authentication实例
        UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication(username, null, List.of(authority));
        // 将Authentication对象添加到SecurityContext中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 调用过滤器中的下一个过滤器
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 将此过滤器配置为在请求/login路径是不触发
        return request.getServletPath().equals("/login");
    }
}
