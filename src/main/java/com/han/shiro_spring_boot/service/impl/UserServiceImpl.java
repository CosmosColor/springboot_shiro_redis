package com.han.shiro_spring_boot.service.impl;



import com.han.shiro_spring_boot.bean.Perms;
import com.han.shiro_spring_boot.bean.Role;
import com.han.shiro_spring_boot.bean.User;
import com.han.shiro_spring_boot.mapper.UserMapper;
import com.han.shiro_spring_boot.service.UserService;
import com.han.shiro_spring_boot.utils.saltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userDao;
    @Override
    public void register(User user) {
        String salt= saltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {

        return userDao.findUserByName(username);
    }

    @Override
    public User findRolesByName(String username) {
        return userDao.findRolesByName(username);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer id) {
       return userDao.findPermsByRoleId(id);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int addRoleforUserById(Integer userid, Integer roleid) {
        return userDao.addRoleforUserById(userid,roleid);
    }

    @Override
    public int deleteRoleByUserId(Integer id) {
        return userDao.deleteRoleByUserId(id);
    }

    @Override
    public List<User> getUsersSimple() {
        return userDao.getUsersSimple();
    }

    @Override
    public List<Role> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public int getUserCounts() {
        return userDao.getUserCount();
    }

}
