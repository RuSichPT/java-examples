package com.github.rusichpt.filter.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MyRequestLogFilter extends AbstractRequestLoggingFilter {
    private static final List<String> EXCLUDE_URLS = Arrays.asList("/actuator", "/swagger-ui", "/v3/api-docs");

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        // URL запросы по которым не логировать
        final String requestURI = request.getRequestURI();
        for (String prefixUrl : EXCLUDE_URLS) {
            if (requestURI.startsWith(prefixUrl)) {
                return false;
            }
        }
        return true;
    }
}
