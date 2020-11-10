package com.deiksoftdev.automatagame.resourceserving;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    public StaticResourceConfiguration() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        this.addDefault(registry);
    }

    private void addDefault(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/unity/**")
                .addResourceLocations("/unity/")
                .setCachePeriod(1) // TODO: Lengthen intentionally short cache period
                .resourceChain(true)
                .addResolver(new EncodedResourceResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }
}