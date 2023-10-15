# 쇼핑의 시대

## 프로젝트 설명
```c
전자상거래 프로젝트
유저 관리(권한 관리)
카테고리 관리
아이템 관리(아이템 옵션)
쿠폰 관리
```

## 기술스택
```c
1.Kotlin
2.JPA
3.Spring Boot, Spring Security
4.Gradle
5.H2 인메모리 DB
```

## jar 파일 위치
/shoppera.jar

## 서버 구동 방법
java -jar /shoppera

## 빌드 방법
WORK_DIR :  shoppera

jar 파일 빌드 \
./gradlew build


## 빌드 후 서버 구동 방법
빌드된 jar 파일 실행 \
java -jar ./shoppera/build/libs/shoppera-0.0.1-SNAPSHOT.jar


## API 인터페이스 명세서
http://localhost:8080/swagger-ui/index.html