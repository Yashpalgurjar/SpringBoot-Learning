package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    List<StudentData> students = new ArrayList<>();

    @GetMapping("/students")
    public List<StudentData> getStudents() {
        return students;
    }
    public StudentController() {

        students.add(new StudentData(
                "Yashpal",
                20,
                "Indore",
                "yash@gmail.com",
                9876543210L));

        students.add(new StudentData(
                "Rahul",
                21,
                "Delhi",
                "rahul@gmail.com",
                9876543211L));
    }
    @PostMapping("/students")
    public StudentData createStudent(@RequestBody StudentData student) {

        students.add(student);

        return student;
    }
    @PutMapping("/students/{name}")
    public StudentData updateStudent(@PathVariable("name") String name,
                                     @RequestBody StudentData updatedStudent) {

        for (StudentData student : students) {

            if (student.getName().equalsIgnoreCase(name)) {

                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setCity(updatedStudent.getCity());
                student.setEmail(updatedStudent.getEmail());
                student.setPhone(updatedStudent.getPhone());

                return student;
            }
        }

        return null;
    }
   
    @DeleteMapping("/students/{name}")
    public String deleteStudent(@PathVariable("name") String name) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getName().equalsIgnoreCase(name)) {

                students.remove(i);

                return "Student Deleted Successfully";
            }
        }

        return "Student Not Found";
    }
}