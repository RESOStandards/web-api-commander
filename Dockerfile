FROM gradle:8.0.2-jdk8 AS builder

WORKDIR /home/gradle/project

ADD . ./

RUN gradle jar

RUN ls

FROM alpine:latest

RUN apk add --update bash ca-certificates openjdk8-jre-base nss git  && \
    rm -rf /var/cache/apk/*

COPY --from=builder /home/gradle/project/build/libs/web-api-commander.jar ./

ENTRYPOINT ["java","-jar","/web-api-commander.jar"]
CMD ["--help"]
