package com.han.shiro_spring_boot.controller;


import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;
import com.han.shiro_spring_boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Transactional
    @RequestMapping("/delete/{id}/{pn}")
    public String delete(@PathVariable("id") Integer id, @PathVariable("pn") int pn, HttpSession httpSession){
        if (SecurityUtils.getSubject().isPermitted("admin:del")){
        System.out.println(id);
        System.out.println(pn);
        int i = userService.deleteUserById(id);
        i+=userService.deleteRoleByUserId(id);
        if(i>1) System.out.println("删除成功");
        }else{
            httpSession.setAttribute("errorAuth","没有相应权限");
            return "redirect:/dynamic_table?pn="+pn+"&auth="+false;
        }
        return "redirect:/dynamic_table?pn="+pn;
    }
    @RequestMapping("/addUser/{pn}")
    public String toAdd(Model model,@PathVariable("pn")int pn,HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        boolean flag=subject.isPermitted("admin:add");
        if(!flag) {
            session.setAttribute("errorAuth","没有相应权限");
            return "redirect:/dynamic_table?pn="+pn+"&auth="+false;
        }
        System.out.println("成功进入增加控制器");
        List<Role> roles = userService.getRoles();
        model.addAttribute("roles",roles);
        return "AdminaddUser";
    }
    @RequestMapping("/addSuccess")
    @Transactional
    public String addSuccess(User user, Integer role){
        userService.register(user);
        System.out.println(role);
        User temp = userService.findByUserName(user.getUsername());
        System.out.println(temp);
        userService.addRoleforUserById(temp.getId(),role);
        return "redirect:/dynamic_table";
    }
}
