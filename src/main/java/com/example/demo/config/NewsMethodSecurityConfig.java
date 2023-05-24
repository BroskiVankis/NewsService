package com.example.demo.config;

import com.example.demo.service.NewsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // both pre and post invocations enabled
public class NewsMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final NewsService newsService;

    public NewsMethodSecurityConfig(NewsService newsService) {
        this.newsService = newsService;
    }

    // Evaluating method level security expressions
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new NewsSecurityExpressionHandler(newsService);
    }
}
