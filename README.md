# QuotationService
## Review
The API service allows:
* Creation of a user account using Spring Security (JWT)
* Addition, viewing, getting a random quote, modification, 
and deletion of quotes 
* View of the top and worse 10 quotes

### Technology stack
* Java 17
* Spring Boot
* Spring Security
* Spring validation
* Jsonwebtoken library (jjwt)
* Maven
* Lombok
* Model mapper
* H2 Database
* Flyway
* Log4j

## How to run
* Clone repository
* You should have docker and maven
* Just use start.sh
* To stop application use stop.sh

## Endpoints
<font color='#fa8072'>AUTHENTICATION: /auth general path

* <font color='#5f9ea0'> POST /register
</font>

Registration of a user

If placed on port 8080, the request will look like:
**localhost:8080/auth/register**

* <font color='#5f9ea0'> POST /authenticate</font>


  Authentication of a user

<font color='#fa8072'>QUOTE: /quotes general path

* <font color='#5f9ea0'> POST 
</font>

Creation of a quote

If placed on port 8080, the request will look like:
**localhost:8080/quotes**

* <font color='#5f9ea0'> PUT /{id}</font>
If placed on port 8080, the request will look like:
**localhost:8080/quotes/{id}**

Modification of quote

* <font color='#5f9ea0'> DELETE /{id}</font>

Deleting of a quote

* <font color='#5f9ea0'> GET /{id}</font>

Getting quote by id

* <font color='#5f9ea0'> GET {general path}</font>

Getting list of all quotations

* <font color='#5f9ea0'> GET /pagination</font>

RequestParam Integer page,  
RequestParam Integer limit,  
RequestParam Boolean orderByTop,  
RequestParam Boolean orderByFlop.  
Page and Limit are required

Returns sorted list by likes or dislikes with pagination


* <font color='#5f9ea0'> GET /last</font>

Returns last added quotations

* <font color='#5f9ea0'> GET /random</font>

Returns a random quote

<font color='#fa8072'>SCORE: /quotes - general path</font>

If placed on port 8080, the request will look like:
**localhost:8080/quotes**

* <font color='#5f9ea0'> POST /add-like/{id}</font>

Add like to the quote by id


* <font color='#5f9ea0'> POST /add-dislike/{id}</font>

Add dislike to the quote by id

<font color='#asdg'>Examples of json:   </font>

Register user:
http://localhost:8080/auth/register  
{
"name" : "Test",
"email" : "Test@gmail.com",
"password" : "12345"
}  
Here you will get registration token look like:   
{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY3NjAyMzc0NSwiZXhwIjoxNjc2MDI1MTg1fQ.eMA0pIhhfYzqEl2qDShjCfldwbSK-OZHslGZfY4ZUwc"
}
  
Authenticate user: http://localhost:8080/auth/authenticate  
{
"email" : "Test@gmail.com",
"password" : "12345"
}   
Here you will get registration token look like:  
{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY3NjAyMzc0NSwiZXhwIjoxNjc2MDI1MTg1fQ.eMA0pIhhfYzqEl2qDShjCfldwbSK-OZHslGZfY4ZUwc"
}

Use all next methods after authorization (bearer token)

Register quote:
http://localhost:8080/quotes  
{
"content" : "test1User1",
"user" : {
"email" : "Test@gmail.com"
}
}

Edit quote:
http://localhost:8080/quotes/{id}  
{
"quote" : "New SomeContent"
}

Delete quote:
http://localhost:8080/quotes/{id}  

Get all quotes: http://localhost:8080/quotes

Get all quotes with pagination(limit and page are required):  
http://localhost:8080/quotes/pagination?page=1&limit=5&orderByTop=true&orderByFlop=false
without ordering it will return a list of all quotations

Get a list last quote:
http://localhost:8080/quotes/last

Get a random quote:
http://localhost:8080/quotes/random

Add like to a quote:
http://localhost:8080/quotes/add-like/{id}

Add dislike to a quote:
http://localhost:8080/quotes/add-dislike/{id}








