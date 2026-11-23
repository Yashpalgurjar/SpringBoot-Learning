package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("StudentGradeUtil tests")
class StudentGradeUtilTest {

    @ParameterizedTest(name = "marks {0} should give grade {1}")
    @CsvSource({
            "100, A", "80, A", "75, A",
            "74, B",  "60, B", "50, B",
            "49, C",  "40, C", "35, C",
            "34, F",  "20, F", "0, F"
    })
    @DisplayName("Valid marks return the correct grade")
    void shouldReturnCorrectGrade(int marks, String expectedGrade) {
        assertEquals(expectedGrade, StudentGradeUtil.calculateGrade(marks));
    }

    @ParameterizedTest(name = "marks {0} should throw exception")
    @ValueSource(ints = {-1, -5, 101, 1000})
    @DisplayName("Invalid marks throw IllegalArgumentException")
    void shouldThrowException_whenMarksAreInvalid(int marks) {
        assertThrows(IllegalArgumentException.class,
                () -> StudentGradeUtil.calculateGrade(marks));
    }

    @Test
    @DisplayName("Exception message is correct")
    void shouldHaveCorrectMessage_whenMarksAreInvalid() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> StudentGradeUtil.calculateGrade(-1));
        assertEquals("Marks must be between 0 and 100", ex.getMessage());
    }
}