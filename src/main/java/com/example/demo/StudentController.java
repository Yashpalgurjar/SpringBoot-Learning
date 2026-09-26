package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.dto.StudentSummaryDTO;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @GetMapping("/students")
    public Page<StudentResponseDTO> getStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable("id") Long id) {

        return studentService.getStudentById(id);
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
                minAge,
                maxAge
        );
    }

    @GetMapping("/students/jpql/cities")
    public List<StudentData> getStudentsByCitiesJPQL(
            @RequestParam("cities") List<String> cities) {

        return studentService.getStudentsByCitiesJPQL(cities);
    }

    @GetMapping("/students/jpql/city-not-null")
    public List<StudentData> getStudentsWithNonNullCityJPQL() {
        return studentService.getStudentsWithNonNullCityJPQL();
    }

    @GetMapping("/students/jpql/cities-not-in")
    public List<StudentData> getStudentsNotInCitiesJPQL(
            @RequestParam("cities") List<String> cities) {

        return studentService.getStudentsNotInCitiesJPQL(cities);
    }

    @GetMapping("/students/jpql/search-not")
    public List<StudentData> getStudentsNameNotLikeJPQL(
            @RequestParam("name") String name) {

        return studentService.getStudentsNameNotLikeJPQL(name);
    }

    @GetMapping("/students/jpql/age-greater-equal/{age}")
    public List<StudentData> getStudentsByAgeGreaterOrEqualJPQL(
            @PathVariable("age") int age) {

        return studentService.getStudentsByAgeGreaterOrEqualJPQL(age);
    }

    @GetMapping("/students/jpql/count")
    public long countAllStudentsJPQL() {
        return studentService.countAllStudentsJPQL();
    }

    @GetMapping("/students/jpql")
    public List<StudentData> findAllStudentsJPQL() {
        return studentService.findAllStudentsJPQL();
    }

    @GetMapping("/students/age")
    public List<StudentData> findStudentsByAge(
            @RequestParam("age") int age) {

        return studentService.getStudentsByAgeJPQL(age);
    }

    @GetMapping("/students/age/greater")
    public List<StudentData> findStudentsByAgeGreaterThan(
            @RequestParam("age") int age) {

        return studentService.getStudentsByAgeGreaterThan(age);
    }

    @GetMapping("/students/age/greater-equal")
    public List<StudentData> findStudentsByAgeGreaterOrEqualJPQL(
            @RequestParam("age") int age) {

        return studentService.getStudentsByAgeGreaterOrEqualJPQL(age);
    }

    @GetMapping("/students/city-age")
    public List<StudentData> findByCityAndAgeJPQL(
            @RequestParam("city") String city,
            @RequestParam("age") int age) {

        return studentService.getStudentsByCityAndAgeJPQL(city, age);
    }

    @GetMapping("/students/city-or-age")
    public List<StudentData> findByCityOrAgeJPQL(
            @RequestParam("city") String city,
            @RequestParam("age") int age) {

        return studentService.getStudentsByCityOrAgeJPQL(city, age);
    }

    @GetMapping("/students/summary")
    public List<StudentSummaryDTO> getStudentSummaries() {
        return studentService.getStudentSummaries();
    }
    @PreAuthorize("hasAuthority('STUDENT_CREATE')")
    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO response =
                studentService.saveStudent(studentRequestDTO);

        return ResponseEntity
                .status(201)
                .body(response);
    }
    @PreAuthorize("hasAuthority('STUDENT_UPDATE')")
    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable("id") Long id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        return studentService.updateStudent(id, studentRequestDTO);
    }
    @PreAuthorize("hasAuthority('STUDENT_UPDATE')")
    @PutMapping("/students/{id}/city")
    public int updateStudentCity(
            @PathVariable("id") Long id,
            @RequestParam("city") String city) {

        return studentService.updateStudentCity(id, city);
    }
    @PreAuthorize("hasAuthority('STUDENT_UPDATE')")
    @DeleteMapping("/students/{id:\\d+}")
    public String deleteStudent(
            @PathVariable("id") Long id) {

        return studentService.deleteStudent(id);
    }
}