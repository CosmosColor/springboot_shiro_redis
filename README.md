# springboot_shiro_redis
练习小项目
jdk 1.8
mysql 8.0.27
使用了redis作为shiro的缓存管理
前端框架使用了thymleaf

主要练习使用shiro进行权限认证


具有管理员增加成员的权限admin:add
删除成员的权限admin:del
管理员增加成员的同时给增加的成员赋予相应角色
注册的用户默认为user角色

测试账户
admin账户
admin 123
product账户
product角色可以进入管理用户界面dynamic_table，但是不可对用户进行修改
root 123456
user账户
无法进入相应管理界面，不具备任何权限
test01 123456