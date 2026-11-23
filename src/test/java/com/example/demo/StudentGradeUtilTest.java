package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.demo.StudentGradeUtil;

class StudentGradeUtilTest {

    @Test
    void shouldReturnGradeA_whenMarksAre80() {
        assertEquals("A", StudentGradeUtil.calculateGrade(80));
    }

    @Test
    void shouldReturnGradeA_whenMarksAre75() {
        assertEquals("A", StudentGradeUtil.calculateGrade(75));
    }

    @Test
    void shouldReturnGradeB_whenMarksAre60() {
        assertEquals("B", StudentGradeUtil.calculateGrade(60));
    }

    @Test
    void shouldReturnGradeC_whenMarksAre40() {
        assertEquals("C", StudentGradeUtil.calculateGrade(40));
    }

    @Test
    void shouldReturnGradeF_whenMarksAre20() {
        assertEquals("F", StudentGradeUtil.calculateGrade(20));
    }

    @Test
    void shouldThrowException_whenMarksAreNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> StudentGradeUtil.calculateGrade(-5));
    }

    @Test
    void shouldThrowException_whenMarksAreAbove100() {
        assertThrows(IllegalArgumentException.class,
                () -> StudentGradeUtil.calculateGrade(101));
    }
}