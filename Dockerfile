FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD rdplat.jar /app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
