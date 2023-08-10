# Demo Task for Aston

# Run

Run by `./mvnw spring-boot:run`

# REST API

See API endpoints at http://localhost:8080/swagger-ui/index.html#/

## Execute queries

You may take some queries with dummy data from `DemoRequests.postman_collection.json` file and import it to Postman

# Solutions:

- DDD-like style of package structure, DTO's namings, Entity and Domain class separating, using Value-objects (```Pin```) for making task more interesting
- Domain Event - ```OperationDoneEvent``` that allows to separate saving transaction in history from making financial operation
- Using in-memory H2 database to simplify development
- Simple database structure with only two tables : `account` and `transaction_history`. For Withdraw and Deposit operation `receivingAccountId` should be NULL 
- Using `springdoc-openapi-starter-webmvc-ui` dependency instead swagger because of some incompatibility Spring Boot 3.0 and Swagger 2.0

## Fill database

To fill database with some data you may uncomment line at `db/changelog/changelog-root.xml` or execute file `db/changelog/scripts/fill.sql` directly


## Time Spent

12h 30m
