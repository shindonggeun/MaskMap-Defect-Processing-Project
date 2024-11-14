package com.mirero.globalmodule.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.mongodb")
public record MongoProperties(
    boolean enabled,
    String host,
    int port,
    String databaseName,
    String userName,
    String password,
    String authDatabase
) {
}


