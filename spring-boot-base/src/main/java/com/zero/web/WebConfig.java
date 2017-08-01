package com.zero.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/5/9
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/*").excludePathPatterns("/login.json")
                .excludePathPatterns("/register.json").excludePathPatterns("/404").excludePathPatterns("/405")
                .excludePathPatterns("/500").excludePathPatterns("/400").excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**").excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/webjars/**");*/
        super.addInterceptors(registry);
    }
}
