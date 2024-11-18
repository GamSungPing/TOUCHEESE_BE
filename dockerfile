FROM eclipse-temurin:17-jdk

# 타임존 설정
ENV TZ=Asia/Seoul
RUN apt-get update && apt-get install -y tzdata \
    && ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone \
    && apt-get clean

COPY ./build/libs/*SNAPSHOT.jar project.jar

ENTRYPOINT ["java", "-jar", "project.jar"]
