package com.example.demo04.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义过滤器
 */
public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String requestId = servletRequest.getHeader("Request-Id");
        // 如果请求头中不包含Request-Id，则将状态改为400，否则进入下一个过滤器
        if (null == requestId || requestId.isBlank()){
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // 如果请求头存在，则请求转发到下一个过滤链
        chain.doFilter(request,response);
    }
}
