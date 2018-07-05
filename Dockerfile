FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD rdplat.jar /rdplat.jar
RUN bash -c 'touch /rdplat.jar'
ENTRYPOINT ["java","-jar","/rdplat.jar"]
