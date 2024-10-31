package com.mirero.ftpservice.ftp.infrastructure.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ftp")
public record FtpProperties(
        String host,
        int port,
        String username,
        String password,
        String remoteDirectory,
        String localDirectory
) {
}
