# School Microservice

This microservice is part of the `GestionEcole` project. It manages the `School` entities with CRUD operations and integrates two external services:

- **Geolocation** service: To convert a school's address into GPS coordinates.
- **Email Validation** service: To verify if the school's email is valid.

### Endpoints

The following endpoints are exposed by the `SchoolController`:

| Method | URL                         | Description                                  |
|--------|-----------------------------|----------------------------------------------|
| GET    | `/schools`                  | Retrieve a list of all schools               |
| GET    | `/schools/{id}`             | Retrieve details of a school by its ID       |
| POST   | `/schools`                  | Create a new school                          |
| PUT    | `/schools/{id}`             | Update an existing school                    |
| DELETE | `/schools/{id}`             | Delete a school                              |
| GET    | `/schools/geo/{id}`         | Retrieve geolocation (GPS coordinates) based on the school's address |
| GET    | `/schools/email-check/{id}` | Validate the email address of the school     |

### External Services
- **Geolocation Service**: Converts the school's address into latitude and longitude coordinates.
- **Email Validation Service**: Verifies the validity of the school's email.

### Tech Stack
- Spring Boot for the backend
- MySQL for the database
- Eureka for service discovery
- API Gateway for routing
