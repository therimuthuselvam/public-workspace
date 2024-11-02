# Blockchain Based Voting System (BVS)

The Blockchain Based Voting System (BVS) leverages the security features of blockchain technology to ensure a transparent and tamper-proof voting process. In this system, each block in the blockchain represents a vote, ensuring that votes are securely stored and cannot be altered.

## Features

The system supports the following actions via APIs, which can be tested using Postman:

- **Register Voter**: Allows new voters to register in the system.
- **Register Candidate**: Enables the registration of candidates for elections.
- **Fetch Candidates**: Retrieves the list of registered candidates.
- **Fetch Voters**: Fetches the list of registered voters.
- **Fetch Blockchain**: Returns the blockchain in JSON format for external validation.
- **Validate Blockchain**: Ensures the integrity of the blockchain.
- **Cast Vote**: Allows authenticated users to cast their votes.

### Security

The application is configured with **Spring Security** to ensure that only authenticated users can vote and perform actions. A **JWT (JSON Web Token)** must be sent with each request from Postman to access secured endpoints.

## Technical Specifications

This project includes the following features:

- **Spring Profiles**: Configurations for different environments.
- **Accurate Logging**: Detailed tracking of execution flow and errors.
- **Spring Data JPA**: Simplifies database interactions and management.
- **Spring Security**: Implements JWT-based authentication for secure access.
- **Spring Boot Actuator**: Monitors application metrics and health.

### Database Management

- **In-Memory Database**: The application is configured to store in-memory database data as JSON during application shutdown and restore the JSON data during startup.
- **Repositories**: `VoteRepository` and `CandidateRepository` handle database-related actions for the respective tables.

### Data Handling

- **DTOs, Model Classes, and Entity Classes**: These have been configured to perform the respective actions seamlessly.
- **Response Handling**: All success and error responses are formatted in JSON for consistency and ease of use.

### Exception Handling

Multiple custom exceptions have been implemented to facilitate debugging and ensure proper error management throughout the application.

### Additional Features

- **Lombok Framework**: Utilized to reduce boilerplate code and enhance code readability.
- **Command Line Runner**: Executes initialization logic to insert sample data if the database is empty and skips execution if data is retrieved from JSON.

### Configuration

Manual configurations are defined in the `application.properties` file to manage application settings.

---

This README serves as a comprehensive guide to the Blockchain Based Voting System, detailing its features, security mechanisms, and technical specifications for developers and users alike.
