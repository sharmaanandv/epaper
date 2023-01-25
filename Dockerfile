FROM maven:3.8.2-jdk-8

# Setting up work directory
WORKDIR /app
COPY .. /app

# Copy the jar/resources files into our app
COPY ./target/epaper.jar epaper.jar
COPY ./target/classes/Epaper.xsd /app/Epaper.xsd

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "epaper.jar"]