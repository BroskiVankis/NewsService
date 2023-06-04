package com.example.demo.config;

import com.example.demo.service.MaintenanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Configuring interceptors -> Components that intercept incoming HTTP requests
    //and HTTP responses, allowing performance of operations before or after request
    //is processed by controller

    //In case i have enough time to implement another language
    private final LocaleChangeInterceptor localeChangeInterceptor;


    private final MaintenanceInterceptor maintenanceInterceptor;

    public WebConfig(LocaleChangeInterceptor localeChangeInterceptor, MaintenanceInterceptor maintenanceInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.maintenanceInterceptor = maintenanceInterceptor;
    }

    //Interceptors will be applied to all incoming requests and responses
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(maintenanceInterceptor);
    }
}
