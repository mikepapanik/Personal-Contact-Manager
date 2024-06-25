
# Personal Contact Manager

## Description
The Personal Contact Manager is a comprehensive full-stack web application developed as part of the Coding Factory 5 program. This application leverages Spring Boot for backend development, ensuring a robust, scalable, and maintainable backend infrastructure. Thymeleaf is utilized for rendering dynamic web pages on the front end, providing a seamless and interactive user experience. The application relies on MySQL as its database management system, offering efficient and reliable data storage and retrieval capabilities. Built using Java 17, the application benefits from modern language features and performance enhancements.

Key features of the Personal Contact Manager include a sophisticated user authentication and authorization system, which ensures secure access to user data. The application exposes RESTful APIs, facilitating smooth and efficient communication between the front-end and back-end components.

The primary goal of the Personal Contact Manager is to empower users to manage their personal contacts efficiently. Users can effortlessly add, update, delete, and search for contacts, making it easy to keep track of important information. Additionally, the application includes event management functionality, allowing users to create, update, and manage events related to their contacts. This dual functionality of contact and event management makes the Personal Contact Manager an invaluable tool for personal organization and productivity.

## Table of Contents
- [Description](#description)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Architecture and Design](#architecture-and-design)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Authors](#authors)
- [License](#license)
- [Screenshots](#screenshots)
- [Detailed Functionality Description](#detailed-functionality-description)

## Installation and Setup

To set up the application locally, follow these detailed steps:

1. **Clone the repository**:
    Open your terminal and execute the following command to clone the repository:
    ```bash
    git clone https://github.com/your-username/personal-contact-manager.git
    ```

2. **Navigate to the project directory**:
    Change your current directory to the project directory:
    ```bash
    cd personal-contact-manager
    ```

3. **Build the project using Maven**:
    Use Maven to clean and build the project. This command will also download all necessary dependencies:
    ```bash
    mvn clean install
    ```

4. **Set up the MySQL database**:
    Access your MySQL server and create a new database for the application:
    ```sql
    CREATE DATABASE personalcontactmanager;
    ```

5. **Configure the Database Credentials:**

   To keep your database credentials secure, you have two options:

   **Option 1: Use an external properties file**

   Create a file named `external.properties` in the `src/main/resources` directory.

   Add the following content to the `external.properties` file and replace `YOUR_DB_USERNAME` and `YOUR_DB_PASSWORD` with your actual database username and password:

   ```properties
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   ```

   Ensure your `application.properties` file includes the following line to load the credentials from the external properties file:

   ```properties
   spring.config.import=optional:classpath:external.properties
   ```

   This file is included in the `.gitignore` to prevent sensitive information from being committed to the repository.

   **Option 2: Directly edit the application.properties file**

   Open the `application.properties` file located in the `src/main/resources` directory and configure it with your MySQL database credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/personalcontactmanager
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   ```
   
6. **Run the application**:
    Start the Spring Boot application using the following Maven command:
    ```bash
    mvn spring-boot:run
    ```

7. **Access the application**:
    Open your web browser and navigate to:
    ```url
    http://localhost:8080
    ```
    This will load the home page of the Personal Contact Manager application.

8. **Generate API Documentation**:
    To generate the Javadoc API documentation, run the following Maven command:
    ```bash
    mvn javadoc:javadoc
    ```

    This command will create the API documentation in the `target/site/apidocs` directory. To view the documentation, open the `index.html` file in a web browser:
    ```url
    target/site/apidocs/index.html
    ```

Following these steps will set up the Personal Contact Manager application on your local machine, allowing you to explore and test its features.


## Usage

The Personal Contact Manager application offers a range of features designed to help users manage their personal contacts and associated events efficiently. Below is a detailed overview of the key functionalities available to both regular users and administrators.

### User Functions

- **Home Page**: The home page serves as the central hub of the application, providing users with an overview of its features and facilitating easy navigation to different sections.
  
- **User Registration**: New users can create an account by providing essential information such as username, email, and password. This registration process is secure and straightforward, enabling users to quickly get started with the application. All passwords are stored in the database in an encrypted format to ensure security. Additionally, users are required to set a security question and answer during registration, which are also stored in an encrypted format. This security question helps users recover their accounts in case they forget their password.

- **User Login**: Existing users can securely log in to their accounts using their credentials. This login process ensures that only authorized users have access to their personal data.

- **User Dashboard**: Upon successful login, users are directed to their personalized dashboard. The dashboard provides a comprehensive view of their contacts and events, along with quick access to various management functions.

- **Add Contact**: Users can easily add new contacts by entering relevant details such as name, email, phone number, and more. This feature helps users maintain a well-organized contact list, making it easier to keep in touch with important people.

- **View Contacts**: The application allows users to view a complete list of their contacts. Users can search for specific contacts, and perform actions such as editing or deleting contact information, ensuring their contact list remains up-to-date and accurate.

- **Add Event**: Users can create new events associated with their contacts. By providing details like event title, date, and notes, users can effectively manage and track important events and appointments.

- **View Events**: Users can view all their scheduled events in one place. This feature enables users to manage their events efficiently, with options to edit or delete events as needed, ensuring they never miss an important date.

### Admin Functions

Administrators have access to additional features that allow them to manage the overall system and its users:

- **Admin Dashboard**: The admin dashboard offers a comprehensive overview of the platform’s usage, including key statistics on user activity, contact management, and event scheduling. This dashboard helps administrators monitor the health and performance of the application.

- **User Management**: Administrators can manage all user accounts within the application. This includes viewing user profiles, updating user information, and deleting user accounts if necessary. These capabilities ensure that the platform remains secure and that user data is properly managed.

- **Content Moderation**: Admins are responsible for monitoring and moderating the content generated within the application. This function ensures that all user-generated content adheres to the platform’s guidelines and policies, maintaining a safe and respectful environment for all users.

### Getting Started

To help you get started with the Personal Contact Manager application, follow these detailed steps:

1. **Register a New Account**:
   - Navigate to the registration section by clicking on the "Register" link on the home page.
   - Fill out the registration form with your username, email, and password. Ensure that you use a valid email address for future communication.
   - Click the "Register" button to create your account.
   - All passwords are stored in the database in an encrypted format to ensure security. Additionally, users are required to set a security question and answer during registration, which are also stored in an encrypted format. This security question helps users recover their accounts in case they forget their password.

2. **Log In**:
   - Navigate to the login section by clicking on the "Login" link on the home page.
   - Enter your username and password that you provided during registration.
   - Click the "Login" button to access your account.
   - Upon successful login, you will be directed to your personal dashboard, where you can start managing your contacts and events.

3. **Managing Contacts**:
   - **Add a New Contact**:
     - In your dashboard, click on the "Add Contact" button.
     - Fill out the contact form with the necessary details such as name, email, phone number, and any additional notes.
     - Click "Save" to add the contact to your list.
   - **View Your Contacts**:
     - Click on the "View Contacts" button to see a list of all your contacts.
     - You can search for specific contacts using the search bar.
     - Click on a contact's name to view more details or to edit/delete the contact.
   - **Edit or Delete a Contact**:
     - To edit a contact, click on the "Edit" button next to the contact's details, make the necessary changes, and click "Save".
     - To delete a contact, click on the "Delete" button next to the contact's details and confirm the deletion.

4. **Managing Events**:
   - **Add a New Event**:
     - In your dashboard, click on the "Add Event" button.
     - Fill out the event form with details such as event title, date, associated contact, and any notes.
     - Click "Save" to add the event to your calendar.
   - **View Your Events**:
     - Click on the "View Events" button to see a list of all your events.
     - You can filter events by date or associated contact.
     - Click on an event to view more details or to edit/delete the event.
   - **Edit or Delete an Event**:
     - To edit an event, click on the "Edit" button next to the event's details, make the necessary changes, and click "Save".
     - To delete an event, click on the "Delete" button next to the event's details and confirm the deletion.

5. **Admin Functions** (if you are an administrator):
   - **Access Admin Dashboard**:
     - As an admin, you will have an "Admin Dashboard" link in your menu.
     - Click on this link to access the admin dashboard, where you can view user activity.
   - **Manage Users**:
     - In the admin dashboard, click on the "Manage Users" section.
     - Here you can view a list of all users and delete accounts if necessary.

By following these steps, users and administrators can fully utilize the capabilities of the Personal Contact Manager application, enhancing their personal organization and contact management efficiency.


## Technologies Used

The Personal Contact Manager leverages a sophisticated stack of technologies to deliver a robust, secure, and highly efficient application. Below is a detailed overview of the key technologies used:

- **IntelliJ IDEA 2023.3.2**: Advanced IDE providing coding assistance and developer tools.
- **Java 17**: Modern programming language with performance enhancements.
- **SDK 21**: Ensures compatibility with the latest Java features.
- **Maven**: Manages project dependencies and builds the application.
- **Spring Boot**: Simplifies development with several integrated starters:
  - **Spring Boot Starter Data JPA**: Facilitates interaction with relational databases using the Java Persistence API (JPA).
  - **Spring Boot Starter Thymeleaf**: Integrates Thymeleaf as the templating engine for server-side rendering of web pages.
  - **Spring Boot Starter Web**: Supports the development of web applications and RESTful services.
  - **Spring Boot Starter Security**: Provides robust authentication and authorization mechanisms.
  - **Spring Boot Starter Validation**: Enables input validation using standard annotations.
  - **Spring Boot DevTools**: Enhances the development experience with tools for hot-reloading and automatic restarts.

- **MySQL 8.0.33**: A widely-used relational database management system that ensures efficient data storage, retrieval, and management.
- **MySQL Connector/J**: The official JDBC driver for MySQL, enabling connectivity between the application and the MySQL database.
- **Hibernate**: A framework for mapping an object-oriented domain model to a relational database, used in conjunction with Spring Data JPA for database interactions.
- **Thymeleaf**: A modern server-side Java template engine for web and standalone environments, used for rendering dynamic web pages on the front end.
- **Spring Security**: A powerful security framework that handles user authentication, authorization, and protection against common security threats.
- **BCrypt**: A password-hashing function used to securely store user passwords and security question answers in an encrypted format, ensuring that sensitive data is protected.
- **Thymeleaf Extras Spring Security 6**: Provides integration between Thymeleaf templates and Spring Security, enhancing the security capabilities within the view layer.
- **SpringDoc OpenAPI**: A library for automating the generation of API documentation, leveraging Swagger UI to provide an interactive interface for API exploration and testing.
- **Mockito**: A popular mocking framework used in unit testing to simulate the behavior of complex objects, ensuring the correctness of the application’s logic. This includes:
  - **Mockito Core**
  - **Mockito JUnit Jupiter**
- **Spring Boot Starter Test**: A testing framework that includes essential libraries such as JUnit and Mockito, facilitating comprehensive testing of the application.
- **Spring Security Test**: Provides support for testing Spring Security configurations, ensuring that security aspects are thoroughly validated.


## Architecture and Design

The Personal Contact Manager follows a layered architecture designed to ensure separation of concerns, modularity, and maintainability. Each layer in this architecture has distinct responsibilities, contributing to a clean and efficient structure.

### Layered Architecture

- **Domain Model**: Represents the core business logic and encapsulates the application's primary entities and their relationships. This layer is independent of the presentation and data access layers to ensure that business rules are centralized and isolated.
  
  - **Entities**: Core business objects that represent the data in the application. Examples include User, Contact, and Event.

- **Data Access Object (DAO)**: Manages database interactions by abstracting the data persistence logic. The DAO layer provides a clean API for the service layer, handling CRUD (Create, Read, Update, Delete) operations and complex queries.

- **Data Transfer Object (DTO)**: Facilitates the transfer of data between different layers of the application. DTOs decouple the internal domain model from the external API representation, ensuring that changes in the domain model do not affect API contracts.

- **Service Layer**: Contains the business logic of the application. This layer acts as an intermediary between the controller and DAO layers, orchestrating business processes, validating inputs, and applying business rules consistently.

- **Controller**: Handles HTTP requests and responses. The controller layer maps incoming requests to the appropriate service methods, processes the data, and returns the appropriate views or responses. It is responsible for interacting with the presentation layer and invoking the necessary service methods to fulfill user requests.

- **Mapper**: Facilitates the mapping between domain objects and DTOs. The mapper layer ensures that data is correctly transformed as it moves between different layers of the application.

- **Helper**: Contains utility and helper classes that provide common functionalities used across different parts of the application.

### Authentication and Authorization

The application employs robust authentication and authorization mechanisms to ensure the security of user data and application functionality.

- **Authentication**: Managed using Spring Security, which handles the process of verifying user identities. User credentials are securely stored using BCrypt, ensuring that passwords are hashed before being saved to the database.

- **Authorization**: Also managed by Spring Security, ensuring that users have appropriate access rights to different parts of the application. Roles such as ADMIN and USER are used to restrict access to certain functionalities, ensuring that only authorized users can perform specific actions.

### Front End

The Front End of the Personal Contact Manager is designed to provide an intuitive and responsive user interface. It utilizes the following technologies:

- **Thymeleaf**: A server-side Java template engine used for rendering dynamic web pages. Thymeleaf is tightly integrated with Spring Boot, facilitating the development of robust and maintainable web applications.
- **Bootstrap**: A popular CSS framework used for designing responsive and mobile-first web pages. It ensures that the application has a consistent and visually appealing design across different devices and screen sizes.
- **JavaScript and jQuery**: Used for adding interactivity and enhancing the user experience. jQuery simplifies DOM manipulation and event handling, making it easier to develop dynamic and interactive web pages.
- **HTML5 and CSS3**: The core technologies used for structuring and styling the web pages. HTML5 provides the semantic structure, while CSS3 is used for layout and visual presentation.

## Database Schema

The `personalcontactmanager` database consists of three tables: `users`, `contacts`, and `events`. Below is the detailed schema for each table.

### Users Table

| Column            | Data Type      | Constraints                        |
|-------------------|----------------|------------------------------------|
| user_id           | INT            | AUTO_INCREMENT, PRIMARY KEY        |
| name              | VARCHAR(50)    | NOT NULL                           |
| email             | VARCHAR(50)    | NOT NULL, UNIQUE                   |
| password          | VARCHAR(100)   | NOT NULL                           |
| role              | VARCHAR(20)    |                                    |
| enabled           | BOOLEAN        |                                    |
| image_url         | VARCHAR(255)   |                                    |
| about             | TEXT           |                                    |
| validated         | INT            |                                    |
| secret_question   | VARCHAR(255)   |                                    |
| secret_answer     | VARCHAR(255)   |                                    |

### Contacts Table

| Column         | Data Type    | Constraints                        |
|----------------|--------------|------------------------------------|
| contact_id     | INT          | AUTO_INCREMENT, PRIMARY KEY        |
| name           | VARCHAR(255) |                                    |
| nick_name      | VARCHAR(255) |                                    |
| work           | VARCHAR(255) |                                    |
| email          | VARCHAR(100) |                                    |
| phone          | VARCHAR(255) |                                    |
| image          | VARCHAR(255) |                                    |
| description    | TEXT         |                                    |
| user_id        | INT          | FOREIGN KEY REFERENCES users(user_id) |

### Events Table

| Column       | Data Type    | Constraints                        |
|--------------|--------------|------------------------------------|
| id           | BIGINT       | AUTO_INCREMENT, PRIMARY KEY        |
| title        | VARCHAR(255) | NOT NULL                           |
| start        | DATETIME     |                                    |
| note         | TEXT         |                                    |
| user_id      | INT          | FOREIGN KEY REFERENCES users(user_id) |
| contact_id   | INT          | FOREIGN KEY REFERENCES contacts(contact_id) |

### SQL Scripts

Here are the SQL scripts to create the database and tables:

```sql
CREATE DATABASE IF NOT EXISTS personalcontactmanager;
USE personalcontactmanager;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20),
    enabled BOOLEAN,
    image_url VARCHAR(255),
    about TEXT,
    validated INT,
    secret_question VARCHAR(255),
    secret_answer VARCHAR(255)
);

CREATE TABLE contacts (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    nick_name VARCHAR(255),
    work VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(255),
    image VARCHAR(255),
    description TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    start DATETIME,
    note TEXT,
    user_id INT,
    contact_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (contact_id) REFERENCES contacts(contact_id)
);
```
## API Documentation

The application uses Swagger for API documentation. Swagger provides an interactive interface for exploring and testing the RESTful APIs, making it easier for developers to understand and integrate with the backend services.

- **SpringDoc OpenAPI**: Automatically generates API documentation and integrates with Swagger UI to provide a user-friendly interface for API exploration.

### Configuration

The application configuration is managed using Spring Boot's configuration properties and custom configuration classes. This includes:

- **application.properties**: Centralized configuration file for setting up database connections, server settings, and other application-specific properties.
- **Security Configuration**: Custom security configurations to manage authentication and authorization.

### Design Principles and Patterns

The application employs several key design principles and patterns to enhance its architecture:

- **MVC (Model-View-Controller)**: Separates the internal representations of information from the ways information is presented and accepted from the user.
- **DAO (Data Access Object)**: Provides an abstract interface to the database, allowing for easier testing and maintenance.
- **Service Layer**: Encapsulates business logic and provides a clear API for the controller layer.
- **DTO (Data Transfer Object)**: Transfers data between layers, keeping the domain models decoupled from external representations.
- **Dependency Injection**: Utilized extensively to manage dependencies and promote loose coupling between components.

These architectural choices ensure that the Personal Contact Manager is scalable, maintainable, and extensible, providing a solid foundation for future development and enhancements.


### Swagger UI
The Personal Contact Manager provides a set of RESTful APIs for interacting with the backend services. These APIs allow users to manage their contacts, events, and user accounts programmatically. 

Swagger UI is integrated into the application to provide an interactive documentation interface. It can be accessed at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

For detailed information and testing of the available API endpoints, please visit the Swagger UI. To access and interact with the API documentation, follow these steps:

1. **Open Swagger UI**: Navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) in your web browser.

2. **Authorize**: To use the API endpoints, you may need to authorize by clicking the "Authorize" button and providing the necessary credentials.

3. **Explore and Test**: Use the interactive interface to explore the API documentation and test the endpoints.

## Testing

The application includes unit tests.

### Unit Tests
Unit tests are used to test individual components of the application in isolation. To run the unit tests, use Maven with the following command:
```bash
mvn test
```
The application includes comprehensive testing to ensure reliability and correctness. Unit tests are written to validate the functionality of individual components and services.

- **JUnit**: A popular testing framework used for writing and running unit tests.
- **Mockito**: A mocking framework used to simulate the behavior of complex objects in unit tests, ensuring that the business logic is tested in isolation.
- **Spring Boot Starter Test**: Provides essential libraries and support for testing Spring Boot applications.

## Authors

- **Michail Papanikolas** - [mikepapanik](https://github.com/mikepapanik)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Screenshots

### Database Schema
![Database Schema](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/fb12f464-3f18-4b50-8014-f219a6c92ae4)


### Home Page
![Home Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/2865b857-8552-4e7f-ab52-0cbf3cf6d516)


### Contact Page
![Contact Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/6d63066f-7254-4ce5-9d03-a078623b4856)


### Login Page
![Login Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/3722f629-1c6e-4300-87e0-e4be525df4d7)


### Registration Page
![Registration Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/e3971e98-aa4e-424f-9a96-877d30134d6c)


### Registration Page with Details
![Registration Page with Details](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/d78aa6cc-d711-4a5c-ba0a-b6cd429c26f6)


### Forgot Password Page
![Forgot Password Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/78b8ac1f-9da4-4ccf-81d7-8455d0ef3c9f)


### Forgot Password Page with Email
![Forgot Password Page with Email](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/c954395c-3ef2-4244-aa06-e960657c59c1)


### Answer Secret Question Page
![Answer Secret Question Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/1ed8de32-7309-472c-9672-17f29b6dd91e)


### Reset Password Page
![Reset Password Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/9d8f0104-5503-4d36-b1bf-6b8d7b44ee37)


### Admin Dashboard
![Admin Dashboard](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/116fb8f0-d68f-409e-96ec-853f4f8630a3)


### Admin Dashboard with Event Form
![Admin Dashboard with Event Form](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/33d4c5ec-c235-45e9-b8f0-c183e4c45208)


### Admin Dashboard with Completed Event Form
![Admin Dashboard with Completed Event Form](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/c8785c6f-93e7-46f0-93f4-284897231c51)


### Admin Dashboard - User Management
![Admin Dashboard - User Management](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/ab3dc254-b0e9-4fc9-b364-933036cff65e)


### Admin Dashboard - Delete User Confirmation
![Admin Dashboard - Delete User Confirmation](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/4da573d3-f0c3-4f92-8a00-494f2cc115c8)


### Admin Profile Page
![Admin Profile Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/d26a6234-67cb-42f7-bc6c-3a6e375a2deb)


### Admin Contact Page
![Admin Contact Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/9ac42177-9d06-47b0-bed3-77590453be4c)


### Logout Confirmation Page
![Logout Confirmation Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/3e9b1904-7ce0-4a48-8820-6b1ede96fc1d)


### User Dashboard
![User Dashboard](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/558f252d-8189-420b-accd-2c3019d67008)


### User Dashboard with Event Form
![User Dashboard with Event Form](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/f20a4ad5-3678-4922-8cf5-923c7b93fcc4)


### View Contacts Page
![View Contacts Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/3500acd7-f3b7-4fcc-82ab-53c5ee224d81)


### Search Contacts Page
![Search Contacts Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/e51a279b-f1e2-4f87-b55e-4bea806cbe38)


### View Contact Details Page
![View Contact Details Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/4a499778-8514-40d1-90f8-877da9afaa40)


### Delete Contact Confirmation
![Delete Contact Confirmation](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/db527654-23d4-4608-9981-09f60879c46c)


### Update Contact Page
![Update Contact Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/0b5be804-87ac-41cf-8076-221793b65211)


### Update Contact Page - Edited
![Update Contact Page - Edited](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/2d0cca5d-d297-4bba-9b19-e595c040ffdf)


### Update Successful Page
![Update Successful Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/5f109046-b82d-4f76-860a-7d1b4ac7e4a8)


### Update Contact View Page
![Update Contact View Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/f828afda-0f01-4930-8cd2-2c3a06afb9c4)


### Add Contact Page
![Add Contact Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/2686be7d-0acb-4398-a118-59ea4e597b1f)


### User Profile Page
![User Profile Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/31d20913-51db-49da-af0a-abb0c945ed6b)


### Edit Profile Page
![Edit Profile Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/39386051-730b-488b-a54f-8a6f3ac4cc71)


### Change Password Page
![Change Password Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/645346bf-787d-4f85-ac1a-91bce5cb79f0)


### User Contact Page
![User Contact Page](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/f269bf63-ce73-4d80-9871-5a26c56912c5)


### API Documentation - Swagger UI
![API Documentation - Swagger UI](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/e5608377-5c44-45f0-87e0-59c16f3ada45)


### API Endpoints - Swagger UI
![API Endpoints - Swagger UI](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/8ac9e057-6733-455a-95d4-0a8e8ebfb6d2)


### Running Tests with Maven
![Running Tests with Maven](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/44fcfeda-4889-47b1-838c-0fb6e41ff8a5)


### Maven Test Results
![Maven Test Results](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/aec030a4-3b10-4244-ac9a-d70f6544bdd9)


### Javadoc Documentation Overview
![Javadoc Documentation Overview](https://github.com/mikepapanik/Personal-Contact-Manager/assets/139591052/69f25515-670e-4a74-a8ad-0c0b528f0ac9)


## Detailed Functionality Description

### User Management
- **User Account Creation**: Allows users to register on the platform.
- **User Login**: Allows users to log in to the platform.
- **User Profile Update**: Allows users to update their profile information.
- **User Password Change**: Allows users to change their password.
- **User Password Recovery**: Allows users to recover their password using a security question.

### Contact Management
- **Add Contact**: Allows users to add new contacts.
- **Update Contact**: Allows users to update the details of a contact.
- **Delete Contact**: Allows users to delete a contact.
- **View Contacts List**: Allows users to view the list of their contacts.

### Event Management
- **Add Event**: Allows users to add new events.
- **Update Event**: Allows users to update the details of an event.
- **Delete Event**: Allows users to delete an event.
- **View Events List**: Allows users to view the list of their events.

### Search
- **Search Contacts**: Allows users to search for contacts based on specific criteria.
- **Search Users**: Allows administrators to search for users based on specific criteria.

### Admin Functions
- **Admin Dashboard**: Allows administrators to view an overview of the platform's usage.
- **User Management**: Allows administrators to manage user accounts, including viewing profiles and deleting users. Additionally, administrators have the same functionalities as regular users, including managing their own contacts and events.

### Controllers Summary

1. **AdminController**
    - **User Management**: Allows administrators to manage user accounts, including viewing user profiles and deleting user accounts. Administrators have all the functionalities of regular users, allowing them to manage their own contacts and events as well.

2. **ContactController**
    - **Contact Management**: Manages all contact-related operations, enabling users to add new contacts, update existing contact details, delete contacts, and view a list of their contacts. Ensures efficient handling and storage of user contacts.

3. **CustomErrorController**
    - **Error Handling**: Handles the display and management of custom error pages. Ensures that users receive meaningful error messages and assists in troubleshooting issues within the application.

4. **EventController**
    - **Event Management**: Manages all event-related operations, allowing users to create new events, update event details, delete events, and view a list of their events. Facilitates seamless event scheduling and tracking.

5. **HomeController**
    - **Main Page Management**: Manages the primary pages of the application, including the home page, about page, user registration page, and user login page. Handles user registration and login processes, ensuring secure access to the platform.

6. **PasswordResetController**
    - **Password Recovery**: Manages the password recovery process. Provides pages and functionality for users to initiate password recovery, verify security questions, and reset their passwords, ensuring account security and user convenience.

7. **SearchController**
    - **Search Functionality**: Provides robust search capabilities for finding contacts and users based on specific criteria. Facilitates quick and efficient searching, helping users to easily locate the information they need.

8. **UserController**
    - **User Dashboard and Profile Management**: Manages user-specific functions, including displaying the user dashboard, adding new contacts, updating contact information, viewing and updating user profiles, and changing passwords. Ensures users have full control over their account settings and personal data.
