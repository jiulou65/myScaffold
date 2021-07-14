package com.jiulou.security;

import com.jiulou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * spring security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService<User> userService;

    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许基于HttpServletRequest使用限制访问
        http.cors().and().csrf().disable();
        http.authorizeRequests().
            antMatchers("/getUser").hasAuthority("query_user").
            antMatchers("/**").fullyAuthenticated();
//                    withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                @Override
//                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                    o.setAccessDecisionManager(accessDecisionManager);//决策管理器
//                    o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
//                    return o;
//                }
//            }).
//             //登出
//                    and().logout().
//            permitAll().//允许所有用户
//            logoutSuccessHandler(logoutSuccessHandler).//登出成功处理逻辑
//            deleteCookies("JSESSIONID").//登出之后删除cookie
//             //登入
//                    and().formLogin().
//            permitAll().//允许所有用户
//            successHandler(authenticationSuccessHandler).//登录成功处理逻辑
//            failureHandler(authenticationFailureHandler).//登录失败处理逻辑
//             //异常处理(权限拒绝、登录失效等)
//                    and().exceptionHandling().
//            accessDeniedHandler(accessDeniedHandler).//权限拒绝处理逻辑
//            authenticationEntryPoint(authenticationEntryPoint).//匿名用户访问无权限资源时的异常处理
//             //会话管理
//                    and().sessionManagement().
//            maximumSessions(1).//同一账号同时登录最大用户数
//            expiredSessionStrategy(sessionInformationExpiredStrategy);//会话失效(账号被挤下线)处理逻辑
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);

    }

}
