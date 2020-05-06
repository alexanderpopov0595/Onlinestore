FROM tomcat:9.0-jre8
COPY /target/OnlineStore.war /usr/local/tomcat/webapps/
