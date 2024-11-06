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
            ConsumerPropertiesInner properties
    ) {}

    public record ConsumerPropertiesInner(
            TrustedPackageConfig spring
    ) {}

    public record TrustedPackageConfig(
            JsonSettings json
    ) {}

    public record JsonSettings(
            PackageTrustSettings trusted
    ) {}

    public record PackageTrustSettings(
            String packages
    ) {}
}
