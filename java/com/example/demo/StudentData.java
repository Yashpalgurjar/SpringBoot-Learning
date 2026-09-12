package com.example.demo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "City should not be blank")
    private String city;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email should not be blank")
    private String email;

    private Long phone;

    @OneToMany(mappedBy = "student")
    private List<Course> courses;
}