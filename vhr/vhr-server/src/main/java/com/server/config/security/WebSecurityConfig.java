package com.server.config.security;

import com.server.service.impl.HrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrServiceImpl hrService;

    @Autowired
    FormLoginSuccessHandler formLoginSuccessHandler;
    @Autowired
    FormLoginErrorHandler formLoginErrorHandler;
    @Autowired
    FormLoginLogoutSuccessHandler formLoginLogoutSuccessHandler;
    @Autowired
    CustomSecurityMetadataSource customSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;
    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customSecurityMetadataSource);
                        return object;
                    }
                })

                // ??????????????????
                .and()
                .formLogin()
                // ???????????????
                .successHandler(formLoginSuccessHandler)
                // ???????????????
                .failureHandler(formLoginErrorHandler)
                .permitAll()

                // ??????
                .and()
                .logout()
                .logoutSuccessHandler(formLoginLogoutSuccessHandler)
                .permitAll()

                // ??????????????????????????????
                .and()
                .csrf().disable()
                // 403??????
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                // ?????????????????????????????????????????????
                .and().
                exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
    }
}
