# Stage 1: Build the application with Maven
FROM amazoncorretto:17 AS build

# Install Maven dependencies (optional, might not be necessary)
RUN yum install -y tar which gzip # TODO remove

# Set up Maven environment variables
ENV MAVEN_HOME /usr/share/maven

# Use Maven to copy the necessary files
COPY --from=maven:3.9.4-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.4-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.4-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

# Create symlink for mvn command
RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# Set Maven environment variables
ARG MAVEN_VERSION=3.9.4
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]

# Stage 1: Set working directory and copy files for building the application
WORKDIR /workspace

# Copy the Maven pom.xml and other files
COPY pom.xml /workspace
COPY ./ /workspace

# Build the project with Maven
#RUN mvn compile
#RUN mvn test
#RUN mvn package
RUN mvn compile -DskipTests
RUN mvn package -DskipTests

# Copy the built JAR file from the target folder to the working directory
RUN cp /workspace/target/springboot-thymeleaf-crud-web-app-0.0.1-SNAPSHOT.jar ./app.jar

# Stage 2: Create the final image with Java runtime
FROM amazoncorretto:17

# Set working directory in the final image
WORKDIR /workspace

# Copy the JAR file from the build stage into the final image
COPY --from=build /workspace/app.jar /workspace/app.jar

# Expose the application port
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

