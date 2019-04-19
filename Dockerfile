FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY target/mn-samples*.jar mn-samples.jar
COPY wait-for wait-for
EXPOSE 8080
CMD ./wait-for -t 60 $CONSUL_HOST:$CONSUL_PORT -- \
    ./wait-for -t 60 $MONGO_HOST:$MONGO_PORT -- \
    echo "All dependencies ready. Starting application..." && \
    java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar mn-samples.jar
