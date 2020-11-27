[![Codacy Badge](https://app.codacy.com/project/badge/Grade/d17a322bdefb4925b634d46354985ad9)](https://www.codacy.com/gh/AnatolyRytik/VotingSystem/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AnatolyRytik/VotingSystem&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/AnatolyRytik/VotingSystem.svg?branch=master)](https://travis-ci.org/AnatolyRytik/VotingSystem)

Description
------------
Task: Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed
 * Each restaurant provides new menu each day.
 
Stack
------------
* Java 11
* Maven
* Spring Boot 2.3.4
* Spring Data JPA
* Spring Data REST
* Spring Security
* H2 Database
* JUnit 5
* Lombok

Installation
------------
Clone a repository:
```sh
$ git clone https://github.com/AnatolyRytik/VotingSystem.git
```

 Run program in IDE or just execute it with Maven:

```sh
$ mvn spring-boot:run
```

##### H2 database
* JDBC URL: jdbc:h2:mem:lunch_voting
* user name: sa
* without password 

##### Authentication
* ADMIN  
    * login: "admin@gmail.com"
    * password:"adminpass"
* UserOne
    * login: "userone@yandex.ru"
    * password: "useronepass"
* UserTwo 
    * login: "usertwo@yandex.ru"
    * password: "usertwoopass"

##### Testing
* Postman - https://documenter.getpostman.com/view/13502788/TVmHDzHv