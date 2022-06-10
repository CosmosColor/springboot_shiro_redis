package com.han.shiro_spring_boot.controller;


import com.han.shiro_spring_boot.bean.User;
import com.han.shiro_spring_boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/main.html")
    public String goMain(Model model){
        int n=userService.getUserCounts();
        System.out.println("总用户数"+n);
        model.addAttribute("userCount",n);
        return "main";
    }
    @RequestMapping("/toIndex")
    public String toIndex(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        String msg="";
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
        subject.login(token);
        if(subject.isAuthenticated()){
        return "redirect:/main.html";
        }
        }catch (UnknownAccountException e){
            e.printStackTrace();
            msg="用户名错误";
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
           e.printStackTrace();
            msg="密码错误";
            System.out.println("密码错误");
        }catch (RuntimeException e){
           e.printStackTrace();
        }
        model.addAttribute("msg",msg);
        return "login";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLogin";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/registerSuccess")
    public String registerSuccess(User user){
        System.out.println("进入注册控制器");
        try{
        userService.register(user);
            System.out.println("成功注册");
        }catch (Exception e) {
            e.printStackTrace();
            return "register";
        }
        return "redirect:/toLogin";
    }
}
