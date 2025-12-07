# ProjectFlow Backend

ProjectFlow is a Spring Boot service that helps teams plan, track, and analyze agile work. It models projects, epics, sprints, stories, tasks, subtasks, users, and access control primitives (profiles, privileges, and groups), exposing a REST API that front-end and automation clients can consume.

## Tech Stack
- Java 21 / Spring Boot 3.5
- Spring Data JPA with PostgreSQL
- Flyway for schema migrations
- Spring Security (currently configured to allow all requests)
- MapStruct for DTO/entity mapping
- SpringDoc OpenAPI + Swagger UI for interactive API docs

## Key Features
- CRUD endpoints for every planning entity (projects, epics, sprints, stories, tasks, subtasks).
- Assignment flows for users, groups, and privileges.
- Sprint analytics endpoints (burndown, velocity, progress).
- Seed data generator for the `dev` profile to quickly populate demo content.
- Global API response envelopes (`ApiResponse` / `ErrorResponse`) for consistent client handling.

## Getting Started
1. **Prerequisites**
   - Java 21 (`JAVA_HOME` must target your JDK 21 installation).
   - Maven Wrapper (bundled) or Maven 3.9+.
   - PostgreSQL 14+ with a database named `projectflow` (defaults can be overridden via environment variables).

2. **Configuration**
   - Adjust `src/main/resources/application.yml` or provide the following environment variables:
     - `SPRING_DATASOURCE_URL`
     - `SPRING_DATASOURCE_USERNAME`
     - `SPRING_DATASOURCE_PASSWORD`
     - `SERVER_PORT` (optional)
   - Flyway migrations live under `src/main/resources/db/migration`. They are disabled by default; enable them when needed by toggling `spring.flyway.enabled`.

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   Use `-Dspring-boot.run.profiles=dev` to load the development profile and seed sample data.

4. **Execute tests**
   ```bash
   ./mvnw test
   ```

## API Documentation
- OpenAPI spec: `GET /v3/api-docs`
- Swagger UI: `GET /swagger-ui/index.html`

Controllers and DTOs carry `@Tag`, `@Operation`, and `@Schema` metadata, so the generated documentation is grouped and annotated with meaningful descriptions and examples.

## Security
`SecurityConfig` currently disables CSRF, form login, HTTP Basic, and CORS restrictions while permitting every route. Harden this configuration when authentication/authorization is ready to be enforced.

## Recent Progress
- Replaced all usages of the reserved Postgres keyword `desc` with `description` across Flyway migrations, entities, DTOs, and seed data to keep schema and code aligned.
- Added SpringDoc OpenAPI/Swagger dependency plus a dedicated configuration bean with metadata, contact info, and external documentation links.
- Annotated every controller with `@Tag`/`@Operation` and every DTO/filter/response with `@Schema` so the API contract is fully described within the generated docs.
- Introduced a permissive `SecurityConfig` that disables authentication layers and allows all requests while security requirements are being finalized.

## Overall Project Milestones
- Bootstrapped the ProjectFlow domain model covering projects, epics, stories, tasks, subtasks, users, profiles, privileges, and groups.
- Implemented REST controllers, DTO layers, and service orchestration for CRUD, filtering, and workflow operations.
- Added database migrations and PostgreSQL integration with Flyway, plus dev-mode data seeding for rapid onboarding.
- Integrated MapStruct, Lombok, and standardized API response envelopes to simplify code and payload handling.
- Enabled SpringDoc-driven documentation, Swagger UI, and a security layer prepared for future tightening.
