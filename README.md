# Blog with Spring Boot 2 & Hibernate ORM

## About
This is a demo blog that shows how to work with:
* **[Spring Boot](https://spring.io/projects/spring-boot)**
* **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
* **[Spring Security](https://spring.io/projects/spring-security)**
* **[JWT (JSON Web Token)](https://jwt.io)**
* **[Spring MVC Tests](https://spring.io/guides/gs/testing-web/)**
* **[MySQL Database](https://www.mysql.com)**
* **[RESTful API](https://restfulapi.net)**

## Requirements
This demo is build with with Maven 4.0.0 and Java 8.

## Usage
For now you can start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [http://localhost:8080](http://localhost:8080).
While I'm creating the frontend part of the demo project, you can use [Postman](https://www.postman.com) to make some API queries and get the information.

## Frontend
In progress

## Backend
The authorization is performed using [Spring Security](https://spring.io/projects/spring-security)'s STATEless approach. It was accomplished due to [JWT](https://jwt.io) and removing all dependencies from the Application State.
There are four User Roles with certain Privileges to demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions. Roles hierarchy looks like this: 
* `ROLE_ADMIN`
* `ROLE_MODERATOR`
* `ROLE_USER`
* `ROLE_DISABLED`

###API
No JWT token needed in the request header for next endpoints
```
POST    /sign_up  - registration endpoint
POST    /login    - authentication endpoint
```
A valid JWT token of a user with `ROLE_ADMIN` must be present in the request header for next endpoints
```
GET     /admin/users            - returns detail information for all users
GET     /admin/users/:id        - returns detail information for user with id=:id
PUT     /admin/users/:id        - updates information on user with id=:id
DELETE  /admin/users/:id        - deletes user with id=:id
PUT     /admin/users/:id/roles  - updates roles of user with id=:id
DELETE  /admin/messages/:id     - deletes message with id=:id 
```
A valid JWT token of a user with `ROLE_USER` must be present in the request header for next endpoints
```
GET     /messages               - returns all messages
GET     /messages/:id           - returns message with id=:id
POST    /messages/:id           - adds new message in the database
PUT     /messages               - updates message in the database
DELETE  /messages/:id           - deletes message with id=:id
PUT     /messages/:id/upvote    - adds 1 rating to message with id=:id
PUT     /messages/:id/downvote  - subtracts 1 rating from message with id=:id 
```

### Generating password hashes for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords.