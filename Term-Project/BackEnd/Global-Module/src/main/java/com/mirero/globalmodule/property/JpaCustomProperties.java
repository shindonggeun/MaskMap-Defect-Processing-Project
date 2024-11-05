package com.mirero.globalmodule.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jpa")
public record JpaCustomProperties(
        boolean enabled
) {
}
