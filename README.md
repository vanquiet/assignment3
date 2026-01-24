SpacePort Management System

Java project with JDBC and PostgreSQL.

Topic

Spaceport management (pilots, ships, missions).

Tech

Java, JDBC, PostgreSQL

Architecture

Controller → Service → Repository → Database

Implemented

BaseEntity (abstract class)

Inheritance (Pilot, Ship)

Interfaces (ReadyCheck, FuelConsumable)

Polymorphism (BaseEntity list)

Composition (Mission has Pilot, Ship)

JDBC + PreparedStatement

CRUD operations

Custom exceptions

PostgreSQL + FK

Run

Create DB spaceport_db

Run schema.sql

Add PostgreSQL JDBC driver

Run controller/Main.java