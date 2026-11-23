package com.example.demo;

public class StudentGradeUtil {

    public static String calculateGrade(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        if (marks >= 75) return "A";
        if (marks >= 50) return "B";
        if (marks >= 35) return "C";
        return "F";
    }
}