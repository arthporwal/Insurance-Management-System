
# Insurance Management System

A comprehensive insurance management platform supporting customer onboarding, policy administration, premium tracking, and claims processing. Built as a RESTful backend service with Spring Boot and MySQL.

## Features

- Customer onboarding and management
- Policy administration (create, update, track policies)
- Premium tracking
- Claims processing
- RESTful APIs for frontend–backend communication
- MySQL integration via JPA/Hibernate for optimized data persistence and querying

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Build Tool:** Maven
- **API Testing:** Postman

## Getting Started

### Prerequisites
- Java 17+ *(update if you used a different version)*
- Maven
- MySQL running locally

### Setup
```bash
git clone https://github.com/arthporwal/Insurance-Management-System.git
cd Insurance-Management-System
```

Configure your database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[your_db_name]
spring.datasource.username=[username]
spring.datasource.password=[password]
```

Run the application:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

*(Fill in from your Postman collection — this table is the single most useful thing for a reviewer skimming the repo. Example structure below — replace with your real paths.)*

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Register a new customer |
| GET | `/api/policies` | Get all policies |
| POST | `/api/policies` | Create a new policy |
| GET | `/api/policies/{id}` | Get policy by ID |
| POST | `/api/claims` | Submit a claim |
| GET | `/api/claims/{id}` | Get claim status |

## Project Structure

```
src/main/java/
├── controller/    # REST endpoints
├── service/       # Business logic
├── repository/    # JPA repositories
├── model/         # Entity classes (Customer, Policy, Claim)
```
