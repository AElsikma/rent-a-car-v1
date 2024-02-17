package com.example.rentacarv1.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.util.Locale;

@Configuration
public class MessageSourceConfiguration {

    @Bean
    public LocaleResolver localeResolver() {

        //AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        //acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        //return acceptHeaderLocaleResolver;

        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();;
        messageSource.setBasename("messages");
        return messageSource;
    }


}
