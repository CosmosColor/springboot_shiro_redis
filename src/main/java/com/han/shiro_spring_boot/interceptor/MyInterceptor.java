package com.han.shiro_spring_boot.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤路径中带 ;jsessionid=003EC21DCDFA5169409DB58EE39C0896 的问题
        System.out.println("拦截器消除session使用");
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf(";jsessionid=") != -1) {
            //替换掉路径上的内容, ;jsessionid=003EC21DCDFA5169409DB58EE39C0896
            requestURI = requestURI.replace(";jsessionid=" + request.getSession().getId(),"");
            //重定向一下
            request.getRequestDispatcher(requestURI).forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
