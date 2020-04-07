FROM adoptopenjdk/openjdk11:alpine-jre
LABEL app="helloworld"

ENV SERVICE_DIR=/opt/service
ENV JAVA_OPTS="-Xms512M -Xmx512M"
ENV PORT=8080

EXPOSE $PORT

RUN mkdir -p $SERVICE_DIR

COPY /build/libs/interview-helloworld.jar $SERVICE_DIR/

EXPOSE $PORT/tcp

HEALTHCHECK --interval=60s --timeout=15s \
  CMD curl --fail "http://localhost:${PORT}/actuator/health" || exit 1

WORKDIR $SERVICE_DIR

CMD [ "/bin/sh", "-c", "java $JAVA_OPTS -jar $SERVICE_DIR/interview-helloworld.jar" ]do