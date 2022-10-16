package com.example.demo05.security;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Nicholas Sun
 * @date 2022/10/16 16:33
 */
public class CsrfTokenFilter implements Filter {

    private final Logger logger = Logger.getLogger(CsrfTokenFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object csrf = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) csrf;
        logger.info("CSRF token : " + token.getToken());

        chain.doFilter(request, response);
    }
}
