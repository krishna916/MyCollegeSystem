package com.college.management.config;

import com.college.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web
            .ignoring().antMatchers("/resources/**");

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/professors/**").hasRole("PROFESSORS")
                .antMatchers("/staff/**").hasRole("STAFF")
                .and()
            .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login").permitAll()
                .successHandler(loginSuccessHandler())
                .failureUrl("/loginPage?error=true")
            .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");

    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return ((request, response, authentication) -> response.sendRedirect("/home"));
    }
}
