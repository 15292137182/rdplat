FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD rdplat.jar /rdplat.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
