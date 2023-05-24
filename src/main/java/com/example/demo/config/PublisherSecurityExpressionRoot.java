package com.example.demo.config;

import com.example.demo.service.NewsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;

import java.util.UUID;

public class PublisherSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final Authentication authentication;

    private final NewsService newsService;

    //Storing the filter object for method level security evaluation
    private Object filterObject;

    // Storing the return Object for the same reason as above
    private Object returnObject;

    public PublisherSecurityExpressionRoot(Authentication authentication, NewsService newsService) {
        super(authentication); //invoking the constructor of the parent "SecurityExpressionRoot" nad passing the auth object
        this.authentication = authentication;
        this.newsService = newsService;
    }

    /*Checking if the auth user is the owner of the news item defined by UUID
    * Using the Authentication object to see the users principal(username) and deligate
    * to the NewsService to see if user is the publisher of the news item */
    public boolean isOwner(UUID id) {
        if(authentication.getPrincipal() == null) {
            return false;
        }

        var userName = authentication.getName();

        return newsService.isPublisher(userName, id);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
