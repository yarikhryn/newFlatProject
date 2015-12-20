package org.h6.security;

import org.h6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DuckerLoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Autowired
    private UserService service;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect("/error");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((User) authentication.getPrincipal()).getUsername();
        Cookie authCookie = new Cookie("user_id", service.findByUsername(username).getId().toString());

        authCookie.setHttpOnly(false);
        authCookie.setPath("/");

        response.addCookie(authCookie);
        response.sendRedirect("/");
    }
}
