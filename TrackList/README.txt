
# Usre TrackList Project

This project is a Spring Boot application designed for managing user data. It allows users to create, view, and list user data through a web interface. 
The application tracks the number of page hits and displays saved user data using Thymeleaf templates. Data is stored in an H2 in-memory database.

## Project Structure

src/main/java
├── ca.lambtoncollege.TrackList
│   ├── controllers
│   │   ├── UserController.java           // Handles form input and listing users
│   │   └── PageHitController.java        // Handles page hit API
│   ├── models
│   │   └── User.java                     // Data model for the form
│   ├── repositories
│   │   └── UserRepository.java           // Repository for database operations
│   ├── services
│   │   └── PageHitService.java           // Tracks page hits
│   └── TrackListApplication.java        // Main application file
src/main/resources
├── static
│   └── css
│       └── style.css                     // CSS for styling the pages
├── templates
│   ├── form.html                         // Form page for user input
│   ├── list.html                         // Page displaying saved data and page hits
└── application.properties                // Configuration file
src/test/java
└── ca.lambtoncollege.TrackList
│    └── UserControllerTest.java           // Test for UserController
│    └── PageHitControllerTest.java        // Test for PageHitController
│    └── UserTest.java                     // Test for User model validation
│    └── UserRepositoryTest.java           // Test for UserRepository
│    └── TrackListApplicationTest.java     // Test for TrackListApplication
│ 
├── pom.xml   // Necessary all the dependencies are here for the project




## Requirements and Implementation

1. **Create a Spring MVC application with a form having at least three fields, validated on the server-side:**
   - **Fulfilled by:**  
     - `User.java`: This is the model class for the user data, which includes validation annotations like `@NotNull`, `@Size`, etc.
     - `UserController.java`: This class handles the form submission and validation.

2. **Data persistence into a database when validation passes:**
   - **Fulfilled by:**  
     - `UserRepository.java`: The repository interface extends `JpaRepository`, which handles data persistence to the database (H2 in-memory database).
     - `UserController.java`: The controller handles the form submission and saves valid data to the database.

3. **Page displaying the saved data using Thymeleaf templates:**
   - **Fulfilled by:**  
     - `form.html`: The form page allows users to input data.
     - `list.html`: Displays the list of saved users using Thymeleaf template engine.

4. **Optional GET parameters to filter the list by attributes (up to two filters):**
   - **Fulfilled by:**  
     - `UserRepository.java`: This file contains custom query methods for filtering users based on attributes like name, email, etc.
     - Example filter query: `findByFirstNameAndLastName`.

5. **API to track and display the number of page hits (updated asynchronously every 3 seconds):**
   - **Fulfilled by:**  
     - `PageHitService.java`: This service tracks the number of page hits.
     - `PageHitController.java`: This API controller exposes the number of page hits to the frontend.

6. **Dependency Injection in at least two locations:**
   - **Fulfilled by:**  
     - `UserController.java`: Injects `UserRepository` to handle data persistence.
     - `PageHitController.java`: Injects `PageHitService` to handle page hit tracking.

7. **Use of Lombok in data classes:**
   - **Fulfilled by:**  
     - `User.java`: Lombok annotations like `@Getter`, `@Setter`, `@AllArgsConstructor`, etc., are used to reduce boilerplate code.

8. **CSS or frameworks used for an aesthetically pleasing website:**
   - **Fulfilled by:**  
     - `style.css`: Contains custom styles for the pages.
     - Thymeleaf templates (e.g., `form.html`, `list.html`) are styled using the CSS file.

9. **Unit tests for classes:**
   - **Fulfilled by:**  
     - `UserControllerTest.java`: Tests the functionality of the `UserController` class.
     - `PageHitControllerTest.java`: Tests the `PageHitController` API functionality.
     - `UserTest.java`: Contains unit tests for the validation logic in the `User.java` model.
     - `UserRepositoryTest.java`: Contains unit tests for the repository layer.
     - `TrackListApplicationTest.java`: Contains tests for the overall application setup.

10. **README file detailing the project requirements and file locations:**
    - **Fulfilled by:** This `README.md` file, which includes the project overview and details about how the requirements are implemented.

## Libraries Used and Why They Were Chosen

### 1. **Spring Boot**
   - **Why Chosen:** Spring Boot simplifies the process of developing Java-based web applications. It provides default configurations, easy setup, and rapid development with minimal boilerplate code. Spring Boot integrates well with Spring MVC and is perfect for developing REST APIs and handling server-side business logic.
   - **What it Does:** Spring Boot is the backbone of the project, enabling rapid application development with embedded servers, auto-configuration, and embedded databases (like H2). It handles web requests, manages the backend logic, and integrates with databases seamlessly.

### 2. **Thymeleaf**
   - **Why Chosen:** Thymeleaf is a modern server-side Java template engine used for rendering HTML. It's tightly integrated with Spring Boot and is easy to set up for rendering views in web applications. It provides a natural templating engine that allows for the dynamic generation of HTML based on model data.
   - **What it Does:** Thymeleaf is used to render the HTML pages (`form.html` and `list.html`) dynamically by integrating data from the controller. It allows for easy HTML generation on the server-side, making it ideal for Spring MVC applications.

### 3. **H2 Database**
   - **Why Chosen:** H2 is an in-memory database that is lightweight and easy to set up. It is perfect for development and testing purposes, as it requires minimal configuration and does not require a separate database server. H2 is fast and can be used for small-scale applications or in-memory testing.
   - **What it Does:** H2 is used as the database for storing user data. Since it's an in-memory database, data is not persistent across application restarts, but it is ideal for development and testing.

### 4. **Spring Data JPA**
   - **Why Chosen:** Spring Data JPA simplifies database operations by providing a higher-level abstraction for working with relational databases using JPA (Java Persistence API). It reduces boilerplate code required for CRUD operations and integrates well with Spring Boot applications.
   - **What it Does:** Spring Data JPA is used to handle database operations such as saving, retrieving, updating, and deleting users. It interacts with the H2 database to persist user data.

### 5. **Lombok**
   - **Why Chosen:** Lombok is a Java library that reduces boilerplate code by generating common methods like getters, setters, constructors, `toString()`, and more through annotations. It simplifies the code and makes it cleaner and easier to maintain.
   - **What it Does:** Lombok is used in the `User.java` model to reduce boilerplate code for getter/setter methods, constructors, and other repetitive code. For example, the `@Getter` and `@Setter` annotations automatically generate getter and setter methods for the fields in the model class.

### 6. **Spring Boot Test**
   - **Why Chosen:** Spring Boot Test provides comprehensive testing support for Spring Boot applications. It helps write unit and integration tests that ensure the application's logic and functionality are working as expected. It supports testing controllers, services, and repositories with the Spring context.
   - **What it Does:** Spring Boot Test is used for writing unit and integration tests for various components of the application, such as `UserControllerTest.java`, `UserRepositoryTest.java`, and `TrackListApplicationTest.java`. It ensures that the application behaves correctly during runtime.

### 7. ** To Access On Broswer 
	-**For User List : http://localhost:8080/list
	-**For User Form : http://localhost:8080/form
	-**For H2- Console Data : http://localhost:8080/h2-console/login.jsp?jsessionid=e390b56e37ef50c5db1a5c58e8855f63
	
		

## Limitations

- **Data Deletion and Update:**
  - The application currently does **not** support data deletion and update directly through the web pages.
  - These operations **must be done manually** using the H2 console.
  
  **Steps to delete or update data using H2 console:**
  1. Access the H2 Console by navigating to `http://localhost:8080/h2-console` in your browser.
  2. Log in with the default JDBC URL (`jdbc:h2:mem:testdb`), username (`sa`), and password (leave it blank).
  3. Use SQL commands to delete or update records:
   
   

