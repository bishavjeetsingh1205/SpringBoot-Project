# Smart Contact - Spring Boot CRUD API

A comprehensive REST API for user management built with Spring Boot, featuring secure password encryption, advanced search capabilities, and complete CRUD operations.

## Features

### Core Features
- ✅ **CRUD Operations** - Create, Read, Update, Delete users
- ✅ **Password Encryption** - Secure password storage using BCrypt
- ✅ **Authentication** - User login verification with encrypted password validation
- ✅ **Advanced Search** - Search users by name, email, role, city, and country
- ✅ **User Validation** - Email validation and duplicate email prevention
- ✅ **Audit Logging** - Track created and updated timestamps
- ✅ **REST API** - RESTful endpoints with proper HTTP status codes
- ✅ **Error Handling** - Comprehensive exception handling with meaningful error messages

### User Model Features
- User ID (Auto-generated)
- Name (Required)
- Email (Required, Unique)
- Password (Encrypted)
- Phone Number
- Role (User, Admin, etc.)
- About/Bio
- Address
- City
- State
- Country
- Status (Active/Inactive)
- Created Timestamp
- Updated Timestamp

## Technology Stack

- **Framework**: Spring Boot 2.x
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA
- **Security**: BCrypt Password Encryption
- **Build Tool**: Maven
- **REST**: Spring Web MVC
- **Validation**: Spring Validation (JSR-380)
- **Logging**: Java Logging Framework
- **Lombok**: For reducing boilerplate code

## Project Structure

```
src/
├── main/
│   ├── java/com/smartContact/
│   │   ├── SmartContactApplication.java (Main Application)
│   │   ├── controller/
│   │   │   └── UserController.java (REST Endpoints)
│   │   ├── service/
│   │   │   ├── UserService.java (Service Interface)
│   │   │   ├── UserServiceImp.java (Service Implementation)
│   │   │   └── PasswordEncryptionService.java (Password Encryption)
│   │   ├── repository/
│   │   │   └── UserDao.java (Data Access Object)
│   │   ├── model/
│   │   │   ├── User.java (User Entity)
│   │   │   └── ErrorMessage.java (Error Response)
│   │   └── error/
│   │       ├── UserNotFoundException.java (Custom Exception)
│   │       └── RestResponseEntityExceptionHandling.java (Exception Handler)
│   └── resources/
│       └── application.properties (Configuration)
└── test/
    └── java/com/smartContact/
        └── SmartContactApplicationTests.java
```

## API Endpoints

### Base URL
```
http://localhost:8082/api/users
```

### 1. Create User
```
POST /api/users/save_user_data
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePass123",
  "phone": "123-456-7890",
  "role": "USER",
  "about": "Software Developer",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "country": "USA",
  "status": "ACTIVE"
}
```

**Response (201 Created):**
```json
{
  "message": "User created successfully",
  "user_id": 1,
  "success": true
}
```

### 2. Get All Users
```
GET /api/users/get_user_data
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "123-456-7890",
    "role": "USER",
    "about": "Software Developer",
    "address": "123 Main St",
    "city": "New York",
    "state": "NY",
    "country": "USA",
    "status": "ACTIVE",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  }
]
```

### 3. Get User by ID
```
GET /api/users/get_user_data/{id}
```

**Example:** `GET /api/users/get_user_data/1`

### 4. Update User
```
PUT /api/users/update_user_data/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "phone": "987-654-3210"
}
```

### 5. Delete User
```
DELETE /api/users/delete_user_data/{id}
```

### 6. Search User by Name (Exact Match)
```
GET /api/users/get_user_name/name/{name}
```

**Example:** `GET /api/users/get_user_name/name/John`

### 7. Search User by Email
```
GET /api/users/get_user_by_email/{email}
```

**Example:** `GET /api/users/get_user_by_email/john@example.com`

### 8. Search Users by Name (Contains)
```
GET /api/users/search_users/{name}
```

**Example:** `GET /api/users/search_users/John` (Returns all users with "John" in name)

### 9. Get Users by Role
```
GET /api/users/get_users_by_role/{role}
```

**Example:** `GET /api/users/get_users_by_role/ADMIN`

### 10. Get Users by City
```
GET /api/users/get_users_by_city/{city}
```

**Example:** `GET /api/users/get_users_by_city/New%20York`

### 11. Get Users by Country
```
GET /api/users/get_users_by_country/{country}
```

**Example:** `GET /api/users/get_users_by_country/USA`

### 12. User Login
```
POST /api/users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "SecurePass123"
}
```

**Response (200 OK):**
```json
{
  "message": "Login successful",
  "user_id": 1,
  "user_name": "John Doe",
  "success": true
}
```

**Response (401 Unauthorized):**
```json
{
  "message": "Invalid email or password",
  "success": false
}
```

### 13. Check Email Exists
```
GET /api/users/check_email/{email}
```

**Example:** `GET /api/users/check_email/john@example.com`

**Response:**
```json
{
  "email": "john@example.com",
  "exists": true
}
```

## Setup and Installation

### Prerequisites
- Java 8+ (JDK 1.8 or higher)
- Maven 3.6+
- MySQL 8.0+
- Git

### Step 1: Clone the Repository
```bash
git clone https://github.com/bishavjeetsingh1205/SpringBoot-Project.git
cd SpringBoot-Project
```

### Step 2: Database Setup
Create a MySQL database:
```sql
CREATE DATABASE barcode;
```

### Step 3: Configure Database Connection
Edit `src/main/resources/application.properties`:
```properties
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/barcode
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Step 4: Build the Project
```bash
cd java-crud-api-master/java-crud-api-master
mvn clean install
```

### Step 5: Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8082`

## API Testing

### Using cURL

**Create User:**
```bash
curl -X POST http://localhost:8082/api/users/save_user_data \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","password":"SecurePass123","phone":"123-456-7890"}'
```

**Get All Users:**
```bash
curl -X GET http://localhost:8082/api/users/get_user_data
```

**Login:**
```bash
curl -X POST http://localhost:8082/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"SecurePass123"}'
```

### Using Postman
1. Import the base URL: `http://localhost:8082/api/users`
2. Create requests for each endpoint
3. Set appropriate HTTP methods (GET, POST, PUT, DELETE)
4. Add request bodies for POST/PUT requests

## Security Features

### Password Encryption
- Uses BCrypt algorithm for password hashing
- Passwords are encrypted before storage
- Original passwords are never stored in the database
- Password validation during login uses BCrypt matching

### Input Validation
- Email validation using `@Email` annotation
- Non-blank field validation
- Unique email constraint at database level

### Email Uniqueness
- Email is set as unique constraint in database
- API validates email existence before creating users
- Duplicate email registration prevention

## Error Handling

### Exception Handling
The application includes comprehensive exception handling:

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "message": "User with ID 999 not found"
}
```

### HTTP Status Codes
- **200 OK** - Successful GET/PUT requests
- **201 Created** - Successful POST requests (user created)
- **400 Bad Request** - Invalid input or duplicate email
- **401 Unauthorized** - Invalid login credentials
- **404 Not Found** - User not found
- **500 Internal Server Error** - Server error

## Logging
The application includes comprehensive logging using Java's built-in logging framework:
- User creation, retrieval, update, and deletion operations are logged
- Login attempts are logged
- Email existence checks are logged

View logs in application console output.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Author
**Bishavjeet Singh**
- GitHub: [@bishavjeetsingh1205](https://github.com/bishavjeetsingh1205)

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Support
For issues and questions, please create an issue on GitHub.

## Future Enhancements
- [ ] JWT Token-based authentication
- [ ] Role-based access control (RBAC)
- [ ] Pagination and sorting for list endpoints
- [ ] Swagger/OpenAPI documentation
- [ ] Unit and integration tests coverage
- [ ] API rate limiting
- [ ] Email verification
- [ ] Password reset functionality
- [ ] Two-factor authentication
- [ ] User profile image upload

---

**Last Updated**: January 2024
**Version**: 2.0.0