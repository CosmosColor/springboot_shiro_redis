package com.han.shiro_spring_boot.controller;

import com.han.shiro_spring_boot.bean.Perms;
import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;
import com.han.shiro_spring_boot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class TableController {
    @Autowired
    private UserService userService;
     @RequestMapping("/dynamic_table")
     public String toDynamic(@RequestParam(value = "pn",defaultValue ="1") int pn, Model model,
                             HttpSession session,@RequestParam(value = "auth",defaultValue ="true")boolean auth){
         if(auth)session.removeAttribute("errorAuth");
         Subject subject = SecurityUtils.getSubject();
         boolean[] flags = subject.hasRoles(Arrays.asList("admin","product"));
         boolean flag=false;
         for(int i=0;i< flags.length;i++){
             flag=flag||flags[i];
         }
         if(flag){
             //为admin权限的用户查出所有用户列表
             PageHelper.startPage(pn, 5);
             List<User> users = userService.getUsersSimple();
             PageInfo<User> info = new PageInfo<>(users);
             List<User> list = info.getList();
             List<User> res=new ArrayList<>();
             for(User u:list){
                 User rolesByName = userService.findRolesByName(u.getUsername());
                 List<Role> rtemp=new ArrayList<>();
                 for(Role role:rolesByName.getRoles()){
                     List<Perms> permsByRoleId = userService.findPermsByRoleId(role.getId());
                     role.setPerms(permsByRoleId);
                     rtemp.add(role);
                 }
                 rolesByName.setRoles(rtemp);
                 res.add(rolesByName);
             }
             model.addAttribute("users",res);
             model.addAttribute("page",info);
         return "table/dynamic_table";
         }else{
           return "redirect:/errorAuth";
         }
     }
     @RequestMapping("/toBasic")
    public String toBasic(){
         return "table/basic_table";
     }

    @RequestMapping("/responsive_table")
    public String toResponsive_table(){
         return "/table/responsive_table";
    }
    @RequestMapping("/editable_table")
    public String toEditable_table(){
         return "/table/editable_table";
    }
    @RequestMapping("/Baidumap")
    public String map(){
       return "/map/baidumap";
    }
     @RequestMapping("/errorAuth")
    public String errorAuth(Model model){
         model.addAttribute("errorAuth","没有对应权限");
         return "main";
     }

}
