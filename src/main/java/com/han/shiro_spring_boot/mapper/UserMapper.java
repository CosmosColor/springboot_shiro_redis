package com.han.shiro_spring_boot.mapper;



import com.han.shiro_spring_boot.bean.Perms;
import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);
    User findUserByName(String username);
    User findRolesByName(String username);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(Integer id);
    List<User> getUsers();
    int deleteUserById(Integer id);
    int deleteRoleByUserId(Integer id);
    int addRoleforUserById(@Param("userid") Integer userid, @Param("roleid") Integer roleid);
    List<User> getUsersSimple();
    List<Role> getRoles();
    int getUserCount();
}
