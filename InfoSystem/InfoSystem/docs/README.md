## InfoSystem Documentation

This document covers the public APIs, classes, data models, configuration, and usage examples for the InfoSystem Spring Boot application.

### Overview

- **Tech stack**: Spring Boot (Web), Java, Maven, MySQL (configured), JavaMail-like sending logic (custom code in `EmailService`).
- **Primary features**:
  - Public REST endpoints to verify service health and to request an email send.
  - Data models for email requests and an `Employee` record (currently not exposed via REST).

### Project layout

- `src/main/java/com/Signup/InfoSystemApplication.java`: Application entrypoint
- `src/main/java/com/Signup/EmailController.java`: REST endpoints
- `src/main/java/com/Signup/EmailService.java`: Email sending service logic
- `src/main/java/com/Signup/EmailRequest.java`: DTO for email sending
- `src/main/java/com/Signup/Employee.java`: JPA-mapped entity (not exposed yet)
- `src/main/resources/application.properties`: Database config (and place for future mail settings)

### Prerequisites

- Java 11+ (match your Spring Boot setup)
- Maven (or use the Maven Wrapper `./mvnw`)
- MySQL running if you plan to use the configured datasource

### Setup & Run

From the project directory `InfoSystem/InfoSystem`:

```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

The application starts on `http://localhost:8080` by default.

### Configuration

`src/main/resources/application.properties` contains basic datasource settings:

```properties
spring.datasource.name=employee1
spring.datasource.url=jdbc:mysql://localhost:3307/employee1
spring.datasource.username=root
spring.datasource.password=root
```

If you intend to enable outbound email, prefer configuring mail settings via environment variables or an externalized config mechanism rather than hardcoding credentials. A typical set of properties looks like:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=465
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true
```

Note: The current `EmailService` shows placeholder code and should be refactored to use `JavaMailSender` safely with properties as above.

### Public REST API

- Base URL: `http://localhost:8080`

1) GET `/welcome`
- **Description**: Simple health/info endpoint.
- **Response**: `200 OK` text/plain

Example:

```bash
curl -i http://localhost:8080/welcome
```

2) POST `/sendemail`
- **Description**: Requests an email to be sent using the provided message, subject, and recipient.
- **Request Body (application/json)**:

```json
{
  "message": "Hello there",
  "subject": "Greetings",
  "to": "recipient@example.com"
}
```

- **Responses**:
  - `200 OK`: "Email is send successfully"
  - `500 INTERNAL_SERVER_ERROR`: "Email not send"

Example:

```bash
curl -i \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello","subject":"Test","to":"user@example.com"}' \
  http://localhost:8080/sendemail
```

See the OpenAPI specification at `docs/openapi.yaml` for a formal contract.

### Data Models

#### EmailRequest (`com.Signup.EmailRequest`)

- **Fields**:
  - `message` (String): Email body text
  - `subject` (String): Email subject line
  - `to` (String): Recipient email address

All fields are required by the controller when sending email.

#### Employee (`com.Signup.Employee`)

- Mapped to table `employee` (via `@Table(name = "employee")`).
- **Fields**:
  - `name` (String)
  - `company` (String)
  - `contactNo` (int)
  - `emailAddress` (String)
  - `productInterested` (String)
  - `city` (String)
  - `country` (String)
  - `remark` (String)

Note: Currently not exposed via REST. Add a repository and controller to manage `Employee` if needed.

### Public Classes and Methods

#### `com.Signup.InfoSystemApplication`
- `public static void main(String[] args)`: Boots the Spring application.

#### `com.Signup.EmailController`
- `@RequestMapping("/welcome")`
  - `public String welcome()`: Returns a welcome message.
- `@PostMapping("/sendemail")`
  - `public ResponseEntity<?> send(@RequestBody EmailRequest req)`: Attempts to send an email by delegating to `EmailService`.

#### `com.Signup.EmailService`
- `public boolean emailservice(String subject, String message, String to)`: Sends an email; returns whether the send succeeded.

Implementation note: The current code is illustrative and should be migrated to use Spring's `JavaMailSender` with properly externalized credentials.

### Usage Examples

- **cURL**: See API examples above.

- **Java (RestTemplate)**:

```java
RestTemplate restTemplate = new RestTemplate();

Map<String, String> payload = new HashMap<>();
payload.put("message", "Hello there");
payload.put("subject", "Greetings");
payload.put("to", "recipient@example.com");

ResponseEntity<String> response = restTemplate.postForEntity(
    "http://localhost:8080/sendemail", payload, String.class);

System.out.println(response.getStatusCode());
System.out.println(response.getBody());
```

### OpenAPI

An OpenAPI 3.0 definition is provided at `docs/openapi.yaml`. You can load this file in tools like Swagger UI, Redoc, or the Swagger Editor to explore and test the API.

### References

See `HELP.md` for Spring references and guides.

