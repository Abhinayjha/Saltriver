## InfoSystem

Spring Boot application with simple REST endpoints and an email sending service.

### Quickstart

```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

App runs at `http://localhost:8080`.

### Endpoints

- `GET /welcome` — simple welcome/health message
- `POST /sendemail` — send an email with JSON body `{ message, subject, to }`

### Documentation

- Full documentation: `docs/README.md`
- OpenAPI spec: `docs/openapi.yaml`

### Configuration

See `src/main/resources/application.properties`. For email, externalize credentials; consider Spring Mail properties as described in `docs/README.md`.

