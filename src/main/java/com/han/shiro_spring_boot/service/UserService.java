package com.han.shiro_spring_boot.service;



import com.han.shiro_spring_boot.bean.Perms;
import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;

import java.util.List;


public interface UserService {
    //注册用户方法
    void register(User user);
    //根据用户名查询业务的方法
    User findByUserName(String username);
    User findRolesByName(String username);
    List<User> getUsers();
    List<Perms> findPermsByRoleId(Integer id);
    int deleteUserById(Integer id);
    int addRoleforUserById(Integer userid, Integer roleid);
    int deleteRoleByUserId(Integer id);
    List<User> getUsersSimple();
    List<Role> getRoles();
    int getUserCounts();
}
