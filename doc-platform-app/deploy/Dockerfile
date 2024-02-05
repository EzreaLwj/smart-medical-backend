FROM openjdk:8u171-jdk-alpine

# 作者
MAINTAINER ezreal
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
