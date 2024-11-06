package com.mirero.globalmodule.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 감시 기능을 활성화하는 설정 클래스입니다.
 * 이 설정을 통해 엔티티의 생성 및 변경 시간을 자동으로 관리할 수 있습니다.
 */
@Configuration
@EnableJpaAuditing
@ConditionalOnProperty(prefix = "spring.jpa", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JpaAuditConfig {
    // 이 클래스는 설정을 활성화하는 어노테이션을 포함합니다.
}
