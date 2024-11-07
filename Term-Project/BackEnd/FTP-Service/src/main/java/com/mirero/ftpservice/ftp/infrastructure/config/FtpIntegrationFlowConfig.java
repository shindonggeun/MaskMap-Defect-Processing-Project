package com.mirero.ftpservice.ftp.infrastructure.config;

import com.mirero.globalmodule.common.dto.FileData;
import com.mirero.ftpservice.ftp.application.service.FileProcessingServiceImpl;
import com.mirero.globalmodule.component.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizingMessageSource;
import org.springframework.messaging.PollableChannel;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * FTP 서버에서 파일을 감지하고, 이를 가져와 {@link FileProcessingServiceImpl}에 전달하여 처리하는 통합 흐름을 구성하는 설정 클래스입니다.
 * 이 설정 클래스는 Spring Integration을 사용하여 주기적으로 FTP 서버를 폴링하고,
 * 새로운 파일을 가져와 개별 파일 단위로 병렬 처리하도록 구성합니다.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class FtpIntegrationFlowConfig {

    private final FileProcessingServiceImpl fileProcessingService;
    private final PollableChannel ftpChannel; // QueueChannel로 설정된 FTP 채널
    private final TaskExecutor taskExecutor;
    private final KafkaProducer kafkaProducer;
    private static final String KAFKA_TOPIC = "pasing-data-topic";

    /**
     * FTP 폴링 및 파일 처리를 구성하는 IntegrationFlow 빈을 정의합니다.
     * 이 흐름은 5분마다 FTP 서버를 폴링하여 새 파일을 감지하고,
     * 최대 10개의 파일을 한 번에 가져와 병렬로 처리합니다.
     *
     * @param ftpMessageSource FTP 서버에서 파일을 가져오는 메시지 소스
     * @return FTP 파일 처리를 위한 구성된 IntegrationFlow 객체
     */
    @Bean
    public IntegrationFlow ftpIntegrationFlow(FtpInboundFileSynchronizingMessageSource ftpMessageSource) {
        return IntegrationFlow.from(ftpMessageSource,
                        config -> config.poller(Pollers.fixedDelay(Duration.ofMinutes(60)) // 60분마다 FTP 서버를 감지하여 새 파일을 가져옴
                                .maxMessagesPerPoll(20) // 한 번의 폴링에서 최대 20개의 파일을 처리
                                .taskExecutor(taskExecutor))) // 병렬 처리 활성화
                .channel(ftpChannel) // 파일을 큐 채널로 전송
                .split() // 가져온 파일들을 개별 메시지로 분리
                .channel(c -> c.executor(Executors.newCachedThreadPool())) // 각 파일을 병렬로 처리
                .handle(message -> {
                    // 파일 감지 즉시 개별 파일 처리 로직 실행
                    File file = (File) message.getPayload();
                    String filePath = file.getAbsolutePath();
                    String fileNameWithExt = file.getName();

                    try {
                        // 파일 경로를 FileProcessingService에 전달하여 파일을 처리
                        FileData result = fileProcessingService.processFile(filePath);

                        kafkaProducer.publish(KAFKA_TOPIC, fileNameWithExt, result);

                        // 파일 처리 후 삭제
                        boolean deleted = file.delete();
                        if (!deleted) {
                            log.warn("파일 삭제 실패: {}", filePath);
                        } else {
                            log.info("파일 처리 후 삭제 성공: {}", filePath);
                        }
                    } catch (IOException e) {
                        log.error("Error processing file: {}", filePath, e); // 오류 발생 시 로그 출력
                    }
                })
                .get();
    }

    /**
     * IntegrationFlow의 폴링 작업을 병렬로 실행하기 위한 TaskExecutor 빈을 제공합니다.
     * 이 TaskExecutor는 폴링 작업이 병렬로 실행될 수 있도록 하여, 여러 파일을 동시에 처리할 수 있게 합니다.
     *
     * @return SimpleAsyncTaskExecutor가 설정된 TaskExecutor 객체
     */
    @Bean
    @Primary
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("Integration-");
    }
}
