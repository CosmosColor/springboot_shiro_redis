package com.han.shiro_spring_boot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import com.han.shiro_spring_boot.shiro.cache.RedisCacheManager;
import com.han.shiro_spring_boot.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    //使用shiro页面标签需要加入方言处理
    @Bean(name ="shiroDialect" )
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    //1 创建shiroFilter
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map=new HashMap<>();
        map.put("/**","authc");//authc请求这个资源需要认证和授权
        //map.put("/toLogin","anon");//anon表示可以匿名访问
        map.put("/toIndex","anon");
        map.put("/static/**","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/fonts/**","anon");
        map.put("/images/**","anon");
        map.put("/toRegister","anon");
        map.put("/registerSuccess","anon");
        //默认认证页面路径
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //2 创建SecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(realm);
        return webSecurityManager;
    }
    //3 创建自定义Realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //修改匹配校检匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存管理
//        customerRealm.setCacheManager(new RedisCacheManager());
//        customerRealm.setCachingEnabled(true);//开启全局缓存
//        customerRealm.setAuthenticationCachingEnabled(true);//认证缓存
//        customerRealm.setAuthenticationCacheName("authenticationCache");
//        customerRealm.setAuthorizationCachingEnabled(true);//授权缓存
//        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }
    //缓存管理对象
//    @Bean
//    public EhCacheManager getEhCacheManager(){
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        //配置缓存管理器对象
//        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return ehCacheManager;
//    }


}
