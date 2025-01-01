
# Toucheese Back-End (3팀 감성핑)

### 멋쟁이 사자처럼 로켓단 2기 - Toucheese; 스튜디오 서칭 & 예약 앱

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<br>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
<img src="https://img.shields.io/badge/Amazon%20RDS-527FFF?style=for-the-badge&logo=Amazon%20RDS&logoColor=white">
<img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white">
<br>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white">
<img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white">
<br>
![Firebase](https://img.shields.io/badge/firebase-a08021?style=for-the-badge&logo=firebase&logoColor=ffcd34)
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white">
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
<br> <br>

## 📸 프로젝트 소개

Toucheese는 스튜디오를 고객과 더 쉽게 연결해주는 플랫폼 입니다.

### Toucheese = Touch + Cheese

‘Touch’ 는 셔터 촬영의 순간과 플랫폼을 통해 검색하는 터치의 의미를 가지며, 

‘Cheese’ 는 촬영 시 미소를 짓게하는 의성어를 의미합니다.

터치즈를 통해 원하는 스튜디오를 더 빠르게 찾고, 간편하게 예약해 보세요!

<br>

### 📆 총 개발 기간: 2024.11.14 ~ 2024.12.31 (4개의 스프린트 진행)

### 👀 모바일 클라이언트 Repository 구경하기
- iOS: https://github.com/GamSungPing/TOUCHEESE_iOS
- Android: https://github.com/GamSungPing/TOUCHEESE_AOS

<br>

## ⚙️ 개발 환경

- JDK 17
- SDK corretto-17
- Spring Boot 3.2.11
- IntelliJ IDEA

<br>

## 💡 프로젝트 구조와 주요 기능 소개

### 프로젝트 구조도

![프로젝트 구조도](https://github.com/user-attachments/assets/1e456ad7-cfa7-4612-8797-10c53c41c885)

### 기능별 시퀀스 다이어그램 - 소셜 로그인

![소셜 로그인 시퀀스 다이어그램](https://github.com/user-attachments/assets/838daaa3-8b62-48af-8924-58dcee06a0bf)


### 기능별 플로우차트 (순서도) - 예약 가능 시간 조회

![예약 가능 시간 조회](https://github.com/user-attachments/assets/277cbb2a-b0b4-4091-87bc-da9a3de029cd)


### 기능별 시퀀스 다이어그램 - 예약 확정 / 취소 앱 푸시 메시지 

![예약 푸시 알림 시퀀스 다이어그램](https://github.com/user-attachments/assets/01a5be2f-33c1-4370-a8da-9d9f105f767d)


<br>

## 🍀 프로젝트 실행 방법 (IntelliJ IDEA 권장)

resources 폴더에 아래 application.yml 파일 추가 후, localhost:8080 실행

```yaml
server:
  port: 8080
spring:
  threads:
    virtual:
      enabled: true
  datasource:
    url: jdbc:h2:./db_dev;MODE=MySQL # H2 사용, 혹은 MySQL 권장
    username: user
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    # 쿼리 최적화 옵션
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: true
        default_batch_fetch_size: 30
        highlight_sql: true
        jdbc:
          batch_size: 50

  # redis 설치 필요
  data:
    redis:
      host: localhost
      port: 6379

# 로컬 실행이므로 AWS 계정 없이 임의의 문자열로 대체
# 혹은 S3Config.java 파일을 주석처리
cloud:
  aws:
    credentials:
      access-key: [실제 aws 키 혹은 임의의 문자열]
      secret-key: [실제 aws 키 혹은 임의의 문자열]
    region:
      static: ap-northeast-2 # 서울 (다른 region 상관 없음)

jwt:
  secret: [임의의 영문자+숫자 조합 100자 내외의 문자열]

admin:
  name: [임의의 관리자 ID]
  password: [임의의 관리자 PW]
```

<br>

## 📌 Commit Convention

| 커밋 구분     | 설명                    |
|-----------|-----------------------|
| Feat	     | 기능 추가 및 개선            |
| Fix	      | 버그 수정                 |
| Docs	     | 코드 외 문서 작업            |
| Test	     | 테스트 추가/수정             |
| Build	    | 빌드 프로세스 관련 수정(yml)    |
| Refactor	 | 코드 정리/리팩토링 (기능 수정X)   |
| Merge	    | 머지/충돌 정리 (상세 설명 필수 X) |
| Comment	  | 주석 수정 및 추가 (코드 수정X)   |

ex/ `Feat: 개요 - 상세 설명(옵션)`
