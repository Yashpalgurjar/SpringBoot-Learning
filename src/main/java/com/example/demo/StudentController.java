package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentData> getStudents() {
        return studentService.getAllStudents();
        
    }
    @GetMapping("/students/{id}")
    public StudentData getStudentById(
            @PathVariable("id") Long id) {

        return studentService.getStudentById(id);
    }
    @PostMapping("/students")
    public ResponseEntity<StudentData> createStudent(
            @RequestBody StudentData student) {

        StudentData savedStudent =
                studentService.saveStudent(student);

        return ResponseEntity
                .status(201)
                .body(savedStudent);
    }
    @PutMapping("/students/{id}")
    public StudentData updateStudent(
            @PathVariable("id") Long id,
            @RequestBody StudentData student) {

        return studentService.updateStudent(
                id, student);
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudent(
            @PathVariable("id") Long id) {

        return studentService.deleteStudent(id);
    }
    }
