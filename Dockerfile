
FROM tomcat

MAINTAINER Chaitanya

COPY target/market_place_v1-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/
