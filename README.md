# Objective Challenge

![Status](https://img.shields.io/badge/Status-Up-brightgreen)

## Description
Objective Challenge is an API designed for managing bank accounts. It allows users to create new accounts with an initial balance and perform transactions via debit, credit, and Pix.

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.2**
- **Relational Database**: PostgresSql

## How to Install and Run

#### Prerequisites
1. **Java Development Kit (JDK)**: Version 21 or higher.
2. **Maven**: Ensure Maven is installed and configured in your system.
3. **Docker**: Ensure Docker is installed and running on your system.

#### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/Norton29/ObjetiveChallenge.git
   ```

2. Start PostgresSql using Docker:
   - Run the provided docker-compose.yml file:
   ```bash
   docker-compose up -d
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run Unit and Integration Test:
    ```bash
    mvn verify
    ```
    
5. Run the application:
   ```bash
   mvn spring-boot:run 
   ```

6. Access the application:
   - Open your browser and navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the API documentation.

---

## Example of Use
1. **Create Accounts**: Users can create new accounts with an initial balance.
2. **Search existing account**: Serching for an account by its number.
3. **Create Transactions**: Performs transactions via debit, credit and pix, including applicable fees.

---
