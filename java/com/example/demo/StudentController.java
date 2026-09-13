package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public Page<StudentResponseDTO> getStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }    
    
    
    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable("id") Long id) {

        return  studentService.getStudentById(id);
    }
    @GetMapping("/students/city/{city}")
    public List<StudentData> getStudentsByCity(
            @PathVariable("city") String city) {

        return studentService.getStudentsByCity(city);
    }
    @GetMapping("/students/age/greater/{age}")
    public List<StudentData> getStudentsByAgeGreaterThan(
            @PathVariable("age") int age) {

        return studentService.getStudentsByAgeGreaterThan(age);
    }
    
    @GetMapping("/students/jpql/age/{age}")
    public List<StudentData> getStudentsByAgeJPQL(
            @PathVariable("age") int age) {

        return studentService.getStudentsByAgeJPQL(age);
        }
    @GetMapping("/students/jpql/city/{city}/age/{age}")
    public List<StudentData> getStudentsByCityAndAgeJPQL(
            @PathVariable("city") String city,
            @PathVariable("age") int age) {

        return studentService.getStudentsByCityAndAgeJPQL(city, age);
    }
    @GetMapping("/students/jpql/city-or-age")
    public List<StudentData> getStudentsByCityOrAgeJPQL(
            @RequestParam("city") String city,
            @RequestParam("age") int age) {

        return studentService.getStudentsByCityOrAgeJPQL(city, age);
    }
    @GetMapping("/students/jpql/sort/age")
    public List<StudentData> getStudentsSortedByAgeJPQL() {

        return studentService.getStudentsSortedByAgeJPQL();
    }
    @GetMapping("/students/jpql/sort/age-desc")
    public List<StudentData> getStudentsSortedByAgeDescJPQL() {

        return studentService.getStudentsSortedByAgeDescJPQL();
    }
    @GetMapping("/students/jpql/search")
    public List<StudentData> searchStudentsByNameJPQL(
            @RequestParam("name") String name) {

        return studentService.searchStudentsByNameJPQL(name);
    }
    @GetMapping("/students/jpql/age-range")
    public List<StudentData> getStudentsByAgeRangeJPQL(
            @RequestParam("minAge") int minAge,
            @RequestParam("maxAge") int maxAge) {

        return studentService.getStudentsByAgeRangeJPQL(
                minAge, maxAge);
    }@GetMapping("/students/jpql/cities")
    public List<StudentData> getStudentsByCitiesJPQL(
            @RequestParam("cities") List<String> cities) {

        return studentService.getStudentsByCitiesJPQL(cities);
    }
 // 1. IS NOT NULL
    @GetMapping("/students/jpql/city-not-null")
    public List<StudentData> getStudentsWithNonNullCityJPQL() {

        return studentService.getStudentsWithNonNullCityJPQL();
    }


    // 2. NOT IN
    @GetMapping("/students/jpql/cities-not-in")
    public List<StudentData> getStudentsNotInCitiesJPQL(
            @RequestParam("cities") List<String> cities) {

        return studentService.getStudentsNotInCitiesJPQL(cities);
    }


    // 3. NOT LIKE
    @GetMapping("/students/jpql/search-not")
    public List<StudentData> getStudentsNameNotLikeJPQL(
            @RequestParam("name") String name) {

        return studentService.getStudentsNameNotLikeJPQL(name);
    }


    // 4. Greater than or equal: >=
    @GetMapping("/students/jpql/age-greater-equal/{age}")
    public List<StudentData> getStudentsByAgeGreaterOrEqualJPQL(
            @PathVariable("age") int age) {

        return studentService.getStudentsByAgeGreaterOrEqualJPQL(age);
    }
    
    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO response =
                studentService.saveStudent(studentRequestDTO);

        return ResponseEntity
                .status(201)
                .body(response);
    }
        
    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable("id") Long id,
            @Valid @RequestBody StudentRequestDTO student) {

        return studentService.updateStudent(id, student);
    }    
    @DeleteMapping("/students/{id}")
    public String deleteStudent(
            @PathVariable("id") Long id) {

        return studentService.deleteStudent(id);
    }
    
    }
