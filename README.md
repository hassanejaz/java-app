**Hello World Java App**

This is a java Hello World app which stores a username and its birthday.

# Hello birthday app

Web application that saves username and birthday, and returns hello birthday message. 

# Testing on Local

The app can be ran by simply running the below docker-compose command. It will bring up 2 docker containers for the java app and postgress db.
API calls are executed from console using curl, Postman, browser (GET) etc. Below is the example for some curl commands to be ran after docker-compose command is ran.
```
docker-compose up -d
curl -X PUT http://localhost:8080/hello/hassan -H "Content-Type: application/json" -d '{"dateOfBirth":"1998-02-21"}'
curl -X GET http://localhost:8080/hello/hassan
```




