FROM openjdk:8
MAINTAINER Chaitanya
ADD target/market_place_v1-0.0.1-SNAPSHOT.war  market_place_v1-0.0.1-SNAPSHOT.war
ENTRYPOINT["java", "-jar", "market_place_v1-0.0.1-SNAPSHOT.war"]
