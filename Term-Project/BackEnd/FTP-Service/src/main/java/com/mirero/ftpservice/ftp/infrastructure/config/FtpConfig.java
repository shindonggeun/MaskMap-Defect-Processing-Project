package com.mirero.ftpservice.ftp.infrastructure.config;

import com.mirero.ftpservice.ftp.infrastructure.property.FtpProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.ftp.filters.FtpLastModifiedFileListFilter;
import org.springframework.integration.ftp.filters.FtpPersistentAcceptOnceFileListFilter;
import org.springframework.integration.ftp.filters.FtpSimplePatternFileListFilter;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizer;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizingMessageSource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.metadata.PropertiesPersistingMetadataStore;
import org.springframework.messaging.PollableChannel;

import java.io.File;
import java.time.Duration;

/**
 * FTP 서버와 연결하고 주기적으로 파일을 동기화하기 위한 설정 클래스입니다.
 * FTP 서버에 대한 연결 설정과 파일 필터링 규칙을 정의하고, 새로운 파일을 가져오기 위한 채널을 구성합니다.
 */
@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class FtpConfig {

    private final FtpProperties ftpProperties;

    /**
     * FTP 서버와의 연결을 관리하는 DefaultFtpSessionFactory 빈을 생성합니다.
     * FTP 서버의 호스트, 포트, 사용자 이름, 비밀번호 등을 설정합니다.
     *
     * @return DefaultFtpSessionFactory FTP 서버 세션을 생성하기 위한 팩토리 빈
     */
    @Bean
    public DefaultFtpSessionFactory ftpSessionFactory() {
        DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
        factory.setHost(ftpProperties.host());
        factory.setPort(ftpProperties.port());
        factory.setUsername(ftpProperties.username());
        factory.setPassword(ftpProperties.password());
        return factory;
    }

    /**
     * 파일 메타데이터를 관리하는 PropertiesPersistingMetadataStore를 설정하여
     * 이전에 처리된 파일을 추적하고 중복 파일을 방지합니다.
     */
    @Bean
    public PropertiesPersistingMetadataStore metadataStore() {
        PropertiesPersistingMetadataStore metadataStore = new PropertiesPersistingMetadataStore();
        metadataStore.setBaseDirectory("metadata/store"); // 메타데이터 저장 경로 설정
        return metadataStore;
    }

    /**
     * FTP 서버에서 주기적으로 파일을 동기화하기 위한 FtpInboundFileSynchronizer 빈을 생성합니다.
     * 특정 파일 확장자와 마지막 수정 시간 조건을 적용하여 파일을 필터링하고, 동기화 규칙을 설정합니다.
     *
     * @return FtpInboundFileSynchronizer FTP 서버에서 주기적으로 파일을 동기화하는 구성 빈
     */
    @Bean
    public FtpInboundFileSynchronizer ftpInboundFileSynchronizer() {
        FtpInboundFileSynchronizer synchronizer = new FtpInboundFileSynchronizer(ftpSessionFactory());
        synchronizer.setDeleteRemoteFiles(false); // 원격 FTP 서버에서 파일을 삭제하지 않음
        synchronizer.setRemoteDirectory(ftpProperties.remoteDirectory()); // 원격 FTP 서버의 디렉토리 경로 설정

        // 파일 필터링 설정: 확장자와 수정 시간 기준
        CompositeFileListFilter<FTPFile> compositeFilter = new CompositeFileListFilter<>();
        compositeFilter.addFilter(new FtpSimplePatternFileListFilter("*.{rff, lrf}"));
        compositeFilter.addFilter(new FtpLastModifiedFileListFilter(Duration.ofMinutes(5)));
        compositeFilter.addFilter(new FtpPersistentAcceptOnceFileListFilter(metadataStore(), "remoteFiles_"));

        // 필터를 동기화 설정에 추가
        synchronizer.setFilter(compositeFilter);
        return synchronizer;
    }

    /**
     * FTP 서버에서 가져온 파일을 로컬 디렉토리에 저장하고 처리하기 위한 FtpInboundFileSynchronizingMessageSource 빈을 생성합니다.
     * 동기화된 파일은 지정된 로컬 디렉토리에 저장되며, 동기화 후 로컬에서 파일을 처리할 수 있습니다.
     *
     * @return FtpInboundFileSynchronizingMessageSource FTP 파일 동기화 메시지 소스
     */
    @Bean
    public FtpInboundFileSynchronizingMessageSource ftpMessageSource() {
        FtpInboundFileSynchronizingMessageSource source = new FtpInboundFileSynchronizingMessageSource(ftpInboundFileSynchronizer());
        source.setLocalDirectory(new File(ftpProperties.localDirectory())); // 동기화된 파일을 저장할 로컬 디렉토리
        source.setAutoCreateLocalDirectory(true); // 로컬 디렉토리가 없을 경우 자동 생성
        source.setMaxFetchSize(20); // 한 번에 가져올 최대 파일 개수 설정
        return source;
    }

    /**
     * 파일 처리 큐 채널을 설정합니다. 최대 20개의 메시지만 큐에 저장하여 메모리 사용을 제어합니다.
     *
     * @return PollableChannel QueueChannel 객체
     */
    @Bean(name = "ftpChannel")
    public PollableChannel ftpChannel() {
        return new QueueChannel(20); // 한 번에 최대 20개의 파일을 큐에 저장
    }
}
