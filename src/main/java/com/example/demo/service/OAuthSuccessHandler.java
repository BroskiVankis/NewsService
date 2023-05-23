package com.example.demo.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//build in Spring class used to handle successful auth

@Component
public class OAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserService userService;

    public OAuthSuccessHandler(UserService userService) {
        this.userService = userService;
        setDefaultTargetUrl("/"); // User will be redirected to "/" after successful auth
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        if(authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            var userEmail = oAuth2AuthenticationToken.getPrincipal().getAttribute("email").toString();

            userService.createUserIfNotExist(userEmail);
            userService.login(userEmail);
        }

        super.onAuthenticationSuccess(request,response,chain,authentication);
    }
}
