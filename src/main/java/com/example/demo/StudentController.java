package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/students")
    public ResponseEntity<StudentData> createStudent(
            @RequestBody StudentData student) {

        StudentData savedStudent =
                studentService.saveStudent(student);

        return ResponseEntity
                .status(201)
                .body(savedStudent);
    }
    }
