package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

//Configuration for internationalization if i have enough time to implement it :)
@Configuration
public class I18NConfig {

    //Resolving the users locale based on cookie value
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setCookieName("lang");
        return clr;
    }

    //intercepts the request and allows user to change locale
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/messages"); // "messages.properties" for default locale and "messages_{locale}.properties" for specific locales
        resourceBundleMessageSource.setDefaultEncoding("UTF-8"); // encoding for supporting international characters
        return resourceBundleMessageSource;
    }
}
