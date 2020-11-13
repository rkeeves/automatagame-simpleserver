package com.deiksoftdev.automatagame.resourceserving;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;

@Data
@Configuration
@ConfigurationProperties(prefix = "custom.resources")
public class ResourceServingProperties {

    @Min(0)
    private Integer cachePeriod;
}
