package com.mirero.globalmodule.config;

import com.mirero.globalmodule.property.MongoProperties;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return mongoProperties.databaseName();
    }

    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential(mongoProperties.userName(),
            mongoProperties.authDatabase(), mongoProperties.password().toCharArray());
        ServerAddress serverAddress = new ServerAddress(mongoProperties.host(), mongoProperties.port());
        return MongoClients.create(
            MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(serverAddress)))
                .credential(credential)
                .build()
        );
    }
}
