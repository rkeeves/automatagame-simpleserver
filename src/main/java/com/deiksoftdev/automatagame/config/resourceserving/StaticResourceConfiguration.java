package com.deiksoftdev.automatagame.config.resourceserving;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.util.Optional;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(StaticResourceConfiguration.class);

    private final ResourceServingProperties resourceServingProperties;

    public StaticResourceConfiguration(ResourceServingProperties resourceServingProperties) {
        super();
        this.resourceServingProperties = resourceServingProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        this.addDefault(registry);
    }

    private void addDefault(ResourceHandlerRegistry registry) {
        var cachePeriod = Optional.ofNullable(resourceServingProperties.getCachePeriod()).orElseGet(()->0);
        registry
                .addResourceHandler("/unity/**")
                .addResourceLocations("/unity/")
                .setCachePeriod(cachePeriod)
                .resourceChain(true)
                .addResolver(new EncodedResourceResolver());
        logger.info("Resource cache period was set to {}",cachePeriod);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }
}