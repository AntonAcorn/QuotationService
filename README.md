# QuotationService
## Review
This is an online library, a CRUD (Create, Read, Update, Delete) application 
where users can add, edit, and delete person cards and books. 
They can also assign books to specific individuals. Or just search books. This application is deployed 
on an Amazon Web Services (AWS) Elastic Compute Cloud (EC2) and can be accessed 
through the provided [link](http://mylibrary-env.eba-6fniism4.us-east-1.elasticbeanstalk.com). 
It provides a user-friendly 
and efficient way to manage and organize library resources.


### Technology stack
* Java 17
* Spring Boot
* Maven
* Lombok
* Thymeleaf
* Hibernate validator
* Model mapper
* Postgresql
* Log4j

## How to run
* Clone repository
* You should have docker and maven
* Just use start.sh
* To stop application use stop.sh

### If you want to run it on local machine:


## Endpoints
<font color='#fa8072'>Person: /people - general path

* <font color='#5f9ea0'> GET /people
</font>

Returns list of people

If placed on port 7070, the request will look like:
**localhost:7070/people**

* <font color='#5f9ea0'> GET /people/{id}</font>


  Example: /registration/1 (it will find person by id=1)

* <font color='#5f9ea0'> GET /people/new</font>

 
Returns person creation page

* <font color='#5f9ea0'> POST /people/create</font>


Creation of a new person

* <font color='#5f9ea0'> GET /people/{id}/edit</font>

 
Returns person edition page

* <font color='#5f9ea0'> PATH /people/{id}/</font>

 
Edit person

* <font color='#5f9ea0'> DELETE /people/{id}/</font>


  Delete person

<font color='#fa8072'>Books: /books - general path</font>

If placed on port 7070, the request will look like:
**localhost:7070/books**

* <font color='#5f9ea0'> GET /books</font>
Returns list of books. You can add pagination, limit and sorting
For example, /books?page=1&books_per_page=5&sort_by_Year=true

* <font color='#5f9ea0'> GET /books/{id}</font>

Returns book by id

* <font color='#5f9ea0'> GET /books/new</font>

Returns book creation page

* <font color='#5f9ea0'> POST /books </font>

Create a new book

* <font color='#5f9ea0'> GET /books/{id}/edit </font>

Returns book creation page by id

* <font color='#5f9ea0'> PATCH /books/{id} </font>

Edit book

* <font color='#5f9ea0'> DELETE /books/{id} </font>

Delete book

* <font color='#5f9ea0'> PATCH /books/{id}/release</font>

Release book by id

* <font color='#5f9ea0'> PATCH /books/{id}/assign</font>

Assign book by id

* <font color='#5f9ea0'> GET /books/search</font>

Returns searching page

* <font color='#5f9ea0'> POST /books/search</font>

Find a book












