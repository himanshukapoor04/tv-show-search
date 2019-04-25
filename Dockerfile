FROM maven:3.6.0-jdk-8-alpine as compiler
WORKDIR /usr/src/app
COPY . .
RUN mvn -B clean package

FROM swd847/centos-graal-native-image-rc13 as nativebuilder
COPY --from=compiler /usr/src/app/target /project/
WORKDIR /project
RUN  /opt/graalvm/bin/native-image -J-Djava.util.logging.manager=org.jboss.logmanager.LogManager \
     -J-Dcom.sun.xml.internal.bind.v2.bytecode.ClassTailor.noOptimize=true \
     -H:InitialCollectionPolicy='com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime' \
     -jar tv-shows-service-1.0-SNAPSHOT-runner.jar -J-Djava.util.concurrent.ForkJoinPool.common.parallelism=1 \
     -H:+PrintAnalysisCallTree -H:EnableURLProtocols=http,https -H:-AddAllCharsets \
     -H:-SpawnIsolates -H:+JNI --no-server -H:-UseServiceLoaderFeature -H:+StackTrace --enable-all-security-services \
     && cp -v tv-shows-service-1.0-SNAPSHOT-runner /tmp/tv-shows-service-1.0-SNAPSHOT-runner

FROM registry.fedoraproject.org/fedora-minimal
RUN mkdir -p /work
COPY --from=nativebuilder /tmp/tv-shows-service-1.0-SNAPSHOT-runner /work/application
RUN chmod -R 775 /work
EXPOSE 8080
WORKDIR /work/
ENTRYPOINT ["./application","-Dquarkus.http.host=0.0.0.0"]
