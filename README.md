# Spring Boot Datasource Demo

This project is a Spring Boot application demonstrating the use of different data source configurations using C3P0 or HikariCP with MySQL. It includes examples of how to set up and configure data sources in a Spring Boot application.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Project Structure

The project is organized into multiple submodules, each demonstrating a different way to configure data sources:

- **c3p0-demo**: Demonstrates the configuration of C3P0 as a connection pool.
- **hikari-demo**: Demonstrates the configuration of HikariCP as a connection pool.
- **core-lib-demo**: Contains shared libraries and utilities used by other modules.

## Features

- **C3P0 Configuration**: Example setup for using C3P0 as a connection pool.
- **HikariCP Configuration**: Example setup for using HikariCP as a connection pool.
- **MySQL Integration**: Configures the application to connect to a MySQL database.
- **Spring Boot**: Utilizes Spring Boot for rapid development and easy configuration.

## Prerequisites

- **Java 11**: Ensure that Java 11 is installed on your system. You can download it from [AdoptOpenJDK](https://adoptopenjdk.net/) or [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Maven 3.6.3 or higher**: Maven is required to build and run the project.
- **MySQL**: A running instance of MySQL is needed to connect the application.

## Running the Application

To run the application locally:

1. **Clone the repository**
    ```bash
    git clone https://github.com/arianmtzcu/spring-boot-datasource-demo.git
    cd spring-boot-datasource-demo
    ```

2. **Build the project using Maven:**
    ```bash
    mvn clean install
    ```

3. **Run the C3P0 Demo:**
    ```bash
    cd c3p0-demo
    mvn spring-boot:run
    ```
The application will start on http://localhost:9001/api/v1/c3p0

4. **Run the HikariCP Demo:**
    ```bash
    cd hikari-demo
    mvn spring-boot:run
    ```
The application will start on http://localhost:9002/api/v1/hikari

## Database Connection Pooling Configuration
This project demonstrates how to configure a database connection pool using two popular libraries: **C3P0** and **HikariCP**. These libraries help manage database connections efficiently by maintaining a pool of reusable connections, which improves the performance and scalability of your application.

## For C3P0 Demo: application.yml and c3p0.properties examples

```yaml
### c3p0-demo/src/main/resources/application.yml
spring:
   datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: com.mchange.v2.c3p0.ComboPooledDataSource
      url: jdbc:mysql://localhost:3306/datasource-demo?useSSL=false&serverTimezone=UTC
      username: yourusername
      password: yourpassword
```

```properties
### c3p0-demo/src/main/resources/c3p0.properties
c3p0.initialPoolSize=3
c3p0.minPoolSize=2
c3p0.maxPoolSize=12
c3p0.acquireIncrement=1
c3p0.maxStatements=0
c3p0.maxStatementsPerConnection=0
c3p0.acquireRetryAttempts=1
c3p0.idleConnectionTestPeriod=30000
c3p0.testConnectionOnCheckout=true
c3p0.debugUnreturnedConnectionStackTraces=true
c3p0.preferredTestQuery=SELECT 1 FROM DUAL
```

For HikariCP Demo:
```yaml
### hikari-demo/src/main/resources/application.yml
spring:
   datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/datasource-demo?useSSL=false&serverTimezone=UTC
      username: yourusername
      password: yourpassword
      hikari:
         maximum-pool-size: 10
         minimum-idle: 5
         idle-timeout: 30000
         connection-timeout: 30000
         max-lifetime: 3600000      
```

## Contributing
Contributions are welcome! If you have a feature or example you'd like to add, feel free to fork the repository, make your changes, and submit a pull request.

## License
This project is licensed under the [MIT License](https://opensource.org/license/MIT).
