FROM tomcat:9.0.76-jdk17-corretto

COPY build/libs/liquibase-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
