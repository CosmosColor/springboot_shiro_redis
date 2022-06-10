package com.han.shiro_spring_boot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.context=applicationContext;
    }
    public static <T> T getBean(String beanName,Class<T> o){
        return context.getBean(beanName,o);
    }
    public static Object getBean(String beanName){
     return  getBean(beanName,Object.class);
    }

}
