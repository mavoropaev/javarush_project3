FROM tomcat:9.0.70-jdk17

MAINTAINER Mikhail Voropaev

RUN rm -rf /usr/local/tomcat/webapps/*

EXPOSE 8080

COPY ./target/javarush_project3-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]