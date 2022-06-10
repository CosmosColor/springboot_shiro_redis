package com.han.shiro_spring_boot.shiro.realms;


import com.han.shiro_spring_boot.bean.Perms;
import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;
import com.han.shiro_spring_boot.service.UserService;
import com.han.shiro_spring_boot.shiro.salt.MyByteSource;
import com.han.shiro_spring_boot.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = principalCollection.getPrimaryPrincipal().toString();
            UserService userService= ApplicationContextUtils.getBean("userService",UserService.class);
            //根据用户名获取所拥有的角色
            List<Role> roles = userService.findRolesByName(primaryPrincipal).getRoles();
//            授权角色信息
            if(!CollectionUtils.isEmpty(roles)){
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                roles.forEach(role -> {
                    authorizationInfo.addRole(role.getRoleName());
                    //根据角色获取所拥有的权限信息
                    List<Perms> perms = userService.findPermsByRoleId(role.getId());
                    if(!CollectionUtils.isEmpty(perms)){
                      perms.forEach(perm->{
                          System.out.println(perm.toString());
                          authorizationInfo.addStringPermission(perm.getName());
                      });
                    }
                });

                return authorizationInfo;
            }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        //Object password = String.valueOf((char[])token.getCredentials());
//        System.out.println("username:"+username+"  password"+password);
        UserService userService = ApplicationContextUtils.getBean("userService", UserService.class);
        System.out.println(username);
        User user = userService.findByUserName(username);
        System.out.println(user);
        if(user!=null)
         //在调用方法时进行校检
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        else
        return null;
    }
}
