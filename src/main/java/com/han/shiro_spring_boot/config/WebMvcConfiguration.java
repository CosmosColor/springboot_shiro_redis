package com.han.shiro_spring_boot.config;


import com.han.shiro_spring_boot.interceptor.MyInterceptor;
import org.apache.shiro.io.ResourceUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override   //拦截器配置
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()) //拦截器注册对象
                .addPathPatterns("/*"); //指定要拦截的请求
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("resources/", "static/", "public/",
                        "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/",
                        "classpath:public/", "classpath:META-INF/resources/")
                .addResourceLocations("file:///tmp/webapps/");

    }
}

