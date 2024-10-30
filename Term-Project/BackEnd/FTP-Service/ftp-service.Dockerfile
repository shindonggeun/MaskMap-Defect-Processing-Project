# 빌드 환경 설정 단계
FROM openjdk:21-jdk-slim AS builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 및 리소스 복사
COPY . .

# Gradle Wrapper에 실행 권한 부여
RUN chmod +x ./gradlew

# Spring Boot 애플리케이션 빌드
RUN ./gradlew clean bootJar

# 실행 환경 설정 단계 (최종 이미지)
FROM openjdk:21-jdk-slim

# /app 디렉토리에 필요한 파일만 복사
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# 추가 디렉토리 생성 (예: 비디오 관련 파일 저장)
RUN mkdir -p /app/videos

# 기본 작업 디렉토리 설정
WORKDIR /app

# 프로덕션 프로필을 활성화하여 애플리케이션 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]
