# IAPPS- Epaper Module
Service helps user to upload Epaper XML and persist details ,Also in retrieving details powered with searching, sorting and pagination.

### Prerequisite: Java 8, maven, Docker (Optional)

### Steps to Run application:
Go to project directory, execute below cmds one by one.

- maven build cmd is kept outside docker , so that docker uptime can be improved. Moreover test cases are also passed so can be skipped.
```sh
mvn clean install -DskipTests
```

- Create volume so that even if docker is restarted, data won't be lost. Make sure docker daemon is running.
```sh
docker volume create --name=mysql-volume
```
- Boot application along with mysql instance,  
```sh
docker-compose up --build
```

Incase you want to avoid docker then use below cmd to run spring boot application. In that case, you need to update app.yml with your Mysql credentials. 

```sh
mvn spring-boot:run
```

Here you can see application is running at 8080 port.

Additional cmd- you can take down docker images by using below cmd

```sh
docker-compose down
```

## Swagger 3 - Rest API Documentation
- Swagger UI can be use to view API documentation also to test API's.
- Swagger URL - http://localhost:8080/iapps/swagger-ui/index.html

Note-

getAll API: Pass only those parameters in the request body which need to be filtered.



### Assumptions- 
- In getAll API a user can filter uploadTime by passing uploadDate,As it will be difficult for user to entered exact uploadTime.
   So user can pass uploadDate to see records to uploaded on that date
-  Data type for uploadTime is long, so that Front-end can transform as per need.
- No length validation is added on column type string(default value is 255 char)






