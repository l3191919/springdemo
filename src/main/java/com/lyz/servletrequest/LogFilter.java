package com.lyz.servletrequest;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Order(1)
//@Component
@Slf4j
//@WebFilter(filterName="logFilter", urlPatterns="/*")
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("filter before");
        // 转换成自己覆写的类
        MyRequestWrapper req = new MyRequestWrapper((HttpServletRequest) request);
        log.info("获取到post信息为：{}", request.getReader());
        // 如果没有覆写HttpServletRequestWrapper
        // doFilter(request, response)之后 再用到body
        // 会抛出类似错误 Cannot call getInputStream(), getReader() already called
        // 注意doFilter这里传进去的参数req 而不是request ！！！！
        chain.doFilter(req, response);
        log.info("filter after");
    }

    @Override
    public void destroy() {

    }
}