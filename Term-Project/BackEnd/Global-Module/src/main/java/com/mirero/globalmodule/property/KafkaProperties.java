package com.mirero.globalmodule.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka")
public record KafkaProperties(
        String bootstrapServers,
        ConsumerProperties consumer
) {
    public record ConsumerProperties(
            String groupId,
            String autoOffsetReset,
            Properties properties
    ) {}

    public record Properties(
            Spring spring
    ) {}

    public record Spring(
            Json json
    ) {}

    public record Json(
            Trusted trusted
    ) {}

    public record Trusted(
            String packages
    ) {}
}
