# reward-program
This is a Java Spring Boot project that demonstrates the implementation of a RESTful API with an embedded database. It also integrates Swagger for API documentation.

## Prerequisites

- JDK 21
- Maven installed on your machine

## Technologies Used

- Java
- Spring Boot
- Embedded database (H2)
- Swagger

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/sharath-konkati/reward-program.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd reward-program
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

5. **Access Swagger UI:**

   Once the application is running, you can access Swagger UI by navigating to:

    ```
    http://localhost:8081/swagger-ui/index.html#/
    ```

## Endpoints

The following endpoints are available:

- `GET /transactions/{id}`: Get a transaction record by transaction id
- `POST /transactions`: Create a new transaction record
- `PUT /transactions/{id}`: Update an existing transaction record
- `DELETE /transactions/{id}`: Delete a transaction record
- `GET /users/{userId}/rewards` : Get user reward points

## Configuration

The project uses an embedded H2 database by default. You can modify the database configuration in the `application.properties` file located in the `src/main/resources` directory.

## Swagger Documentation

Swagger is integrated into the project to provide interactive API documentation. You can explore and test the API endpoints using Swagger UI.
