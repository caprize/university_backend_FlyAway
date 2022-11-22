FROM openjdk:17
COPY ./build/libs/Flights-1.0-SNAPSHOT.jar Flights-1.0-SNAPSHOT.jar
CMD ["java","-jar","Flights-1.0-SNAPSHOT.jar"]