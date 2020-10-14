package com.application.springMvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.TimeUnit;

/**
 * Java configuration
 * @author Ihor Savchenko
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.application.springMvc.controller")
public class WebConfig implements WebMvcConfigurer {

    // позволяет получить ресурсы, задав к ним путь в url
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resource/**")
                .addResourceLocations("/staticResources/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("/js/", "/css/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver()
                        .addContentVersionStrategy("/**"));
                          //.addFixedVersionStrategy("1.1.0", "/**/*.js"));
                          //.addTransformer(new CssLinkResourceTransformer());

    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

}
