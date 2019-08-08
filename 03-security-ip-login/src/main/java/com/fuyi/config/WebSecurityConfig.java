package com.fuyi.config;

import com.fuyi.filter.IPAuthenticationProcessingFilter;
import com.fuyi.provider.IPAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //IP认证者
    @Bean
    IPAuthenticationProvider ipAuthenticationProvider() {
        return new IPAuthenticationProvider();
    }

    //配置登录端点
    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/ipLogin");
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(ipAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/ipLogin").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .csrf()
                    .disable()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/ipLogin")
                    .authenticationEntryPoint(loginUrlAuthenticationEntryPoint());

        //注册filter放置顺序
        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    //创建filter
    IPAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        IPAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IPAuthenticationProcessingFilter();
        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/ipLogin?error"));
        return ipAuthenticationProcessingFilter;
    }


}
