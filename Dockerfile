FROM openjdk:17
LABEL maintainer="Marketplace"
ADD target/marketplace-0.0.1-SNAPSHOT.jar springboot-docker-marketplace.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-marketplace.jar"]