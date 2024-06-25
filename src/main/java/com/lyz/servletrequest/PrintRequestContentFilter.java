package com.lyz.servletrequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Created by niugang on 2021-03-26 14:06
 */
//@Order(Ordered.LOWEST_PRECEDENCE)
//@Component
//@WebFilter(filterName = "printRequestContentFilter", urlPatterns = "/*")
@Slf4j
public class PrintRequestContentFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(httpServletRequest);
        log.info("调用inputStream. body的参数: " + new String(wrapper.getContentAsByteArray()));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
