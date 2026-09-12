  package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<StudentData, Long> {

    List<StudentData> findByCity(String city);
    List<StudentData> findByAgeGreaterThan(int age);
}