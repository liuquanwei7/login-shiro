package com.lqw.loginshiro.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @AUTHOR: liuquanwei
 * @DATE: 2019/5/25
 * @TIME: 20:47
 */


@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 添加Shiro内置过滤器：可以实现权限相关的拦截器
         *  anon：无需认证可以访问
         *  authc：必须认证才可以访问
         *  user：如果使用rememberMe的功能可以使用
         *  perms: 该资源必须得到资源权限才可以访问
         *  role：该资源必须得到角色权限才可以使用
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/index","anon");
        filterMap.put("/Login","anon");

        filterMap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
