FROM gradle:jdk8 AS builder

WORKDIR /home/gradle/project

ADD . ./

RUN gradle copyJarToOut

RUN ls

FROM alpine:latest

RUN apk add --update bash ca-certificates openjdk8-jre-base nss && \
    rm -rf /var/cache/apk/*

COPY --from=builder /home/gradle/project/out/web-api-commander.jar ./

ENTRYPOINT ["java","-jar","/web-api-commander.jar"]
CMD ["--help"]



