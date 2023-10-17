package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthLoggingFilter implements Filter {

    // Logger with the name of the filter
    private final Logger logger = Logger.getLogger(AuthLoggingFilter.class.getName());


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var requestId = ((HttpServletRequest) request).getHeader("Request-Id");
        logger.info("Successful authentication with request " + requestId);
        chain.doFilter(request,response);
    }
}
