package org.h6.config;

import org.h6.security.DuckerLoginHandler;
import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class DuckerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DuckerLoginHandler loginHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todo check need of permitting login request, if no remove it
        http.authorizeRequests()
                .antMatchers("/resources/**", "/api/login/**", "/login/**", "/error/**", "/api/user/create", "api/tasks/create").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/api/login")
                .successHandler(loginHandler)
                .failureHandler(loginHandler)
                .permitAll()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/"));

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserService userService) throws Exception {
        auth.userDetailsService(userService);
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }
}