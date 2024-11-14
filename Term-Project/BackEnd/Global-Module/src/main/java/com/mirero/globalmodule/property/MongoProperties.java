package com.mirero.globalmodule.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.mongodb")
public record MongoProperties(
    String host,
    int port,
    String databaseName,
    String userName,
    String password,
    String authDatabase
) {
}
