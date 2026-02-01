SpacePort Management System

About the project
This project is a Java console application for managing a spaceport.
The system allows working with pilots, ships and missions using a PostgreSQL database.
The project was originally created for Assignment 3 and later extended to meet the requirements of Assignment 4.

Technologies
The application is written in Java and uses JDBC to connect to a PostgreSQL database.
PostgreSQL is used as the database.
IntelliJ IDEA was used as the development environment, and the project is stored on GitHub.

Project structure
The project follows a layered architecture.
The controller package contains the entry point of the application.
The service layer contains business logic.
The repository layer is responsible for database operations.
The models package contains entity classes.
Interfaces define common contracts used across the project.

OOP and SOLID
Object-oriented programming principles are used in the project, including abstraction, inheritance, polymorphism and encapsulation.
SOLID principles are applied to keep the code clean, structured and easy to extend.

Advanced Java features
Generics are used in the CrudRepository interface.
Lambda expressions are used for sorting collections.
Reflection is used to inspect class fields and methods at runtime.
Interfaces contain default and static methods.

Run
Run controller/Main.java
