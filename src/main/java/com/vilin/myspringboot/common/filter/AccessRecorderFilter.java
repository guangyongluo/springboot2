package com.vilin.myspringboot.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class AccessRecorderFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(AccessRecorderFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        if(uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".map") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith("woff2")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String client = request.getHeader("user-agent");
        String ip = request.getRemoteAddr();

        Long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        Long endTime = new Date().getTime();
        logger.info("uri: {}, ip: {}, time: {}, client {}", uri, ip, (endTime-startTime), client);
    }
}
