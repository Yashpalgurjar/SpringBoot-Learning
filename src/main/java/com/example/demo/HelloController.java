package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Yashpal";
    }

    @GetMapping("/age")
    public String age() {
        return "Age = 20";
    }

    @GetMapping("/weight")
    public String weight() {
        return "Weight = 50Kg";
    }

    @GetMapping("/name/{name}")
    public String getName(@PathVariable String name) {
        return "Hello " + name;
    }
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name;
        
    }
    
    @PostMapping ("/hello")
    public String helloPost() {
    	
    	return "Hello frome Post";
    }
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {

        return student;

    }
}