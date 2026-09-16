package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
                
    }
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
}
