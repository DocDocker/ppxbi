package com.ppx.ppxshiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@EnableAspectJAutoProxy
@ComponentScan("com.ppx.ppxshiro")
@Configuration
public class ShiroConfiguration {

    /**
     * 将自己的验证方式加入容器
     */

    @Bean(name = "userRealm")
    public UserRealm myShiroRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }


//    /**
//     * 权限管理，配置主要是Realm的管理认证
//     *
//     * @return
//     */
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//        return securityManager;
//    }
    // 添加Shiro内置过滤器
    /**
     * Shiro内置过滤器，可以实现权限相关的拦截器
     *  常用的过滤器：
     *      anon: 无需认证（登录）可以访问
     *      authc: 必须认证才可以访问
     *      user: 如果使用rememberMe功能可以直接访问
     *      perms: 该资源必须得到资源权限才可以访问
     *      role: 该资源必须得到角色权限才可以访问
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();


        Map<String,String> filterMap=new LinkedHashMap<>();
//        filterMap.put("/add", "authc");
//        filterMap.put("/update", "authc");
//        filterMap.put("/user/*", "authc");
        filterMap.put("/hello", "anon");
        filterMap.put("/login", "anon");
//        filterMap.put("/tologin", "anon");

        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * //Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        Map<String, String> map = new HashMap<String, String>();
//        //登出
//        map.put("/logout", "logout");
//        //对所有用户认证
//        map.put("/**", "authc");
//        //登录
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        //首页
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //错误页面，认证不通过跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        return shiroFilterFactoryBean;
//    }

//
//    /**
//     * //加入注解的使用，不加入这个注解不生效
//     *
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
}