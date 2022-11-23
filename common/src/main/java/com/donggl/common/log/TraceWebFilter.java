package com.donggl.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: mdc请求拦截器
 * @author donggl
 * @date 2022/11/10 14:41
 * @version 1.0
 */
@Configuration
public class TraceWebFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(TraceWebFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        TraceUtil.initTrace(request.getHeader(TraceUtil.TRACE_ID));

        LoggerUtil.info(logger, "requestId = {}", request.getHeader(TraceUtil.REQUEST_ID));

        response.addHeader(TraceUtil.TRACE_ID, MDC.get(TraceUtil.TRACE_ID));
        filterChain.doFilter(request, response);
        TraceUtil.clearTrace();
    }
}

