# Spring Boot Learning Project 🚀

This project is created to learn and practice Java Spring Boot backend development step by step.

## 👨‍💻 About Me

Hi, I’m Yashpal Gevhare, a Computer Science Engineering student learning Java Backend Development and Spring Boot.

I am building this project to understand how real-world backend applications work using Spring Boot, Spring Data JPA, MySQL, REST APIs and Postman.

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST API
- Postman
- Git
- GitHub
- Gradle

---

## 📚 Learning Progress

### 1. Spring Boot Basics

- Spring Boot project setup
- Main application class
- `@SpringBootApplication`
- Application properties
- Project structure
- Dependency injection
- Constructor injection
- `@Component`
- `@Service`
- `@Repository`
- `@RestController`

### 2. REST API Development

- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`
- `@PathVariable`
- `@RequestParam`
- `@RequestBody`
- HTTP methods
- HTTP status codes
- Testing APIs using Postman

### 3. Layered Architecture

Implemented the following layers:

- Controller Layer
- Service Layer
- Repository Layer
- Entity/Model Layer
- DTO Layer

### 4. CRUD Operations

Implemented Student CRUD APIs:

- Create student
- Get all students
- Get student by ID
- Update student
- Delete student

### 5. Spring Data JPA

- `@Entity`
- `@Id`
- `@GeneratedValue`
- `JpaRepository`
- Entity mapping
- Database table creation
- Saving data into MySQL
- Fetching data from MySQL
- Updating database records
- Deleting database records

### 6. DTO

Learned and implemented:

- What is DTO?
- Why DTO is required
- Request DTO
- Response DTO
- Entity to DTO mapping
- DTO to Entity mapping
- Keeping entity and API response separate

### 7. Entity Relationships

Learned and practiced:

- One-to-One relationship
- One-to-Many relationship
- Many-to-One relationship
- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@JoinColumn`
- Relationship between Student and Course
- Saving related data
- Fetching related data

### 8. Pagination

Learned and implemented:

- `Pageable`
- `Page<T>`
- Pagination using Spring Data JPA
- Page number
- Page size
- Sorting with pagination
- Paginated API response

### 9. Validation

Learned and practiced:

- `@Valid`
- `@NotBlank`
- `@NotNull`
- `@Min`
- `@Max`
- `@Email`
- Validation in request DTO
- Handling invalid request data

### 10. Exception Handling

Learned and practiced:

- Runtime exceptions
- Custom error messages
- `ResponseEntity`
- `@ExceptionHandler`
- `@RestControllerAdvice`
- Global exception handling
- Handling student not found errors

### 11. Transactions

Learned the basics of:

- `@Transactional`
- Transaction management
- Commit
- Rollback
- Maintaining database consistency

### 12. Derived Query Methods

Practiced custom query methods using method names:

- Find students by name
- Find students by city
- Find students by age
- Find students by email
- Find students by multiple fields
- Greater than queries
- Less than queries
- Between queries
- Sorting through method names

---

## 🔎 JPQL Queries Completed

Learned and practiced JPQL using `@Query`.

### JPQL Operators Practiced

- `AND`
- `OR`
- `ORDER BY ASC`
- `ORDER BY DESC`
- `LIKE`
- `BETWEEN`
- `IN`
- `IS NULL`
- `IS NOT NULL`
- `NOT IN`
- `NOT LIKE`
- Comparison operators
- Greater than `>`
- Greater than or equal to `>=`

### JPQL Practice Work

For each query, implemented:

- Repository query
- Service method
- Controller endpoint
- Postman testing

Example:

```java
@Query("SELECT s FROM StudentData s WHERE s.age >= :age")
List<StudentData> findStudentsByAgeGreaterOrEqual(@Param("age") int age);
