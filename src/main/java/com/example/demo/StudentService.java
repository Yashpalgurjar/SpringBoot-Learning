package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentData> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentData saveStudent(StudentData student) {

        if(student.getAge() < 18) {
            throw new RuntimeException("Age must be 18+");
        }

        return studentRepository.save(student);
    }
}


