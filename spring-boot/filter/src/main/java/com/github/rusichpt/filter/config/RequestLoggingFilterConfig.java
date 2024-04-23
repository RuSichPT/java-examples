package com.github.rusichpt.filter.config;

import com.github.rusichpt.filter.filter.MyRequestLogFilter;
import com.github.rusichpt.filter.filter.RequestCachingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }

    @Bean
    public MyRequestLogFilter myLogFilter() {
        MyRequestLogFilter filter = new MyRequestLogFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA MY LOGGER: ");
        return filter;
    }

    // comment this and uncomment the @Component in the filter class definition to register only for a url pattern
    @Bean
    public FilterRegistrationBean<RequestCachingFilter> loggingFilter() {
        FilterRegistrationBean<RequestCachingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestCachingFilter());
        registrationBean.addUrlPatterns("/taxifare/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }
}
