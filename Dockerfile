FROM openjdk:8-jre-alpine
MAINTAINER Chaitanya
ADD ./target/market_place_v1-0.0.1-SNAPSHOT.war market_place_v1-0.0.1-SNAPSHOT.war
RUN mkdir -p /var/log/spring
RUN touch /var/log/spring/spring-boot-elk.log
CMD ["java","-jar","market_place_v1-0.0.1-SNAPSHOT.war"]
