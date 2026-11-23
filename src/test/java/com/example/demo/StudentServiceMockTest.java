package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.exception.StudentNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StudentService unit tests (Mockito)")
class StudentServiceMockTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Mocks are created and injected")
    void shouldCreateMocksAndInjectThem() {
        assertNotNull(studentRepository);
        assertNotNull(studentService);
    }

    @Test
    @DisplayName("getStudentById returns DTO when student exists")
    void shouldReturnStudent_whenIdExists() {
        // Arrange
        StudentData student = new StudentData();
        student.setId(1L);
        student.setName("Yashu");
        student.setAge(21);
        student.setCity("Indore");
        student.setEmail("yashu@example.com");
        student.setPhone(9999999999L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Act
        StudentResponseDTO response = studentService.getStudentById(1L);

        // Assert
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("Yashu", response.getName());
        assertEquals(21, response.getAge());
        assertEquals("Indore", response.getCity());
        assertEquals("yashu@example.com", response.getEmail());
        assertTrue(response.getCourses().isEmpty());

        verify(studentRepository).findById(1L);
    }

    @Test
    @DisplayName("getStudentById throws exception when student not found")
    void shouldThrowException_whenIdDoesNotExist() {
        // Arrange
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        StudentNotFoundException ex = assertThrows(
                StudentNotFoundException.class,
                () -> studentService.getStudentById(99L));

        assertEquals("Student not found with id: 99", ex.getMessage());
        verify(studentRepository).findById(99L);
    }
}