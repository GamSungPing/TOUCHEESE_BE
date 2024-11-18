FROM eclipse-temurin:17-jdk-alpine

# 타임존 설정
ENV TZ=Asia/Seoul
RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone \
    && apk del tzdata

COPY ./build/libs/*SNAPSHOT.jar project.jar

ENTRYPOINT ["java", "-jar", "project.jar"]
