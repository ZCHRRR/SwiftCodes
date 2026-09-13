# SWIFT Codes API
 
Financial institution SWIFT code registry — REST API built with Spring Boot and PostgreSQL.
 
**Stack:** Java 21, Maven, Spring Boot, Hibernate/JPA, PostgreSQL (Docker), Swagger.
 
**Architecture:** Hexagonal (ports and adapters) — `domain/`, `application/`, `adapter/`.
 
**Features:** CSV import to database, bank lookup by SWIFT code, bank listing by ISO2 country code, record creation and deletion.
 
