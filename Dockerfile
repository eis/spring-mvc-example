FROM tomcat:9.0.62-jdk8-openjdk

ADD src/ /spring-mvc-example/src
ADD pom.xml /spring-mvc-example

#  Build spring app

RUN apt update && apt install maven -y
WORKDIR /spring-mvc-example/
RUN mvn clean package

#  Deploy to tomcat
RUN mv target/ROOT.war /usr/local/tomcat/webapps/


EXPOSE 8080
CMD ["catalina.sh", "run"]
