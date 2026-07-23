# SWIFT Codes API

Rejestr kodów SWIFT instytucji finansowych — REST API oparte o Spring Boot i PostgreSQL.

**Stack:** Java 21, Maven, Spring Boot, Hibernate/JPA, PostgreSQL (Docker), Swagger.

**Architektura:** heksagonalna (porty i adaptery) — `domain/`, `application/`, `adapter/`.

**Funkcje:** import CSV do bazy, pobranie banku po SWIFT code, lista banków po kodzie ISO2, dodanie i usunięcie rekordu.
