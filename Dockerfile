FROM maven:3.6.3
LABEL maintainer="Immanuel Haag <immanuel.haag@novatec-gmbh.de>"
ARG TIME_ZONE=Europe/Berlin
WORKDIR /build
COPY pom.xml .
COPY src/ /build/src
COPY bin/* /build/
# With the latest scala-maven-plugin, it always wants to download certain libs at runtime without which the build fails
# Hence install with offline option not possible currently
# For root cause, see: https://github.com/davidB/scala-maven-plugin/blob/master/src/main/java/sbt_inc/SbtIncrementalCompiler.java#L219-L226
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.1:go-offline && mvn -B install && chmod +x run.sh single.sh
ENTRYPOINT ["./run.sh"]
