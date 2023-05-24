package com.example.demo.config;

import com.example.demo.service.NewsService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class NewsSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final NewsService newsService;

    public NewsSecurityExpressionHandler(NewsService newsService) {
        this.newsService = newsService;
    }

    /*This Method is responsible for creating and configuring the security expression root object
    * Creating an instance of "PublisherSecurityExpressionRoot" by passing "Authentication" object
    * which represents the current user auth details*/
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        PublisherSecurityExpressionRoot root = new PublisherSecurityExpressionRoot(authentication, newsService);

        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
