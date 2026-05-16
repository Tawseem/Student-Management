# Student Management System

A robust, modern Desktop Application built with Java, JavaFX, and Object-Oriented Programming (OOP) principles. It manages student records entirely locally without the need for cloud databases, using JSON file serialization.

## Features
- **Local Data Persistence:** Student records and user credentials are saved automatically in `data/students.json` and `data/users.json`.
- **Authentication:** Login system to secure the application.
- **CRUD Operations:** Add, view, update, and delete students.
- **Search & Filter:** Real-time search by ID, Name, or Course.
- **Statistics Dashboard:** Auto-updating statistics (Total students, average marks, top scorer).
- **Modern GUI:** Clean, responsive UI with customized CSS for a professional look.

## Prerequisites
- **Java Development Kit (JDK) 17** or higher.
- **Maven** (to manage dependencies and build the project).

## How to Run

1. Open a terminal or command prompt in the root of the project directory (where `pom.xml` is located).
2. Clean and compile the project using Maven:
   ```bash
   mvn clean compile
   ```
3. Run the application using the JavaFX Maven Plugin:
   ```bash
   mvn javafx:run
   ```

## Default Credentials
- **Username:** `admin`
- **Password:** `password123`

## Technology Stack
- Java 17
- JavaFX (for UI components)
- Gson (Google's JSON library for data parsing)
- Maven (Build tool)

## Architecture
The application uses a standard MVC (Model-View-Controller) structure:
- **Model:** Represents the data structure (`Student`, `User`).
- **View:** FXML files defining the UI layouts, styled with CSS.
- **Controller:** Binds the View to the backend Services, handling user interactions.
- **Service:** Business logic processing (`StudentManager`, `AuthService`).
- **Data:** Persistence logic mapping POJOs to JSON (`FileHandler`).
