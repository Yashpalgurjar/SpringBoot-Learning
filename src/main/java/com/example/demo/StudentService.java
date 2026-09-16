package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.dto.StudentSummaryDTO;
import com.example.demo.exception.StudentNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentData> getStudentsByAgeJPQL(int age) {
        return studentRepository.findStudentsByAgeJPQL(age);
    }

    public List<StudentData> getStudentsNotInCitiesJPQL(List<String> cities) {
        return studentRepository.findStudentsNotInCitiesJPQL(cities);
    }

    public List<StudentData> getStudentsNameNotLikeJPQL(String name) {
        return studentRepository.findStudentsNameNotLikeJPQL(name);
    }

    public List<StudentData> getStudentsByAgeGreaterOrEqualJPQL(int age) {
        return studentRepository.findStudentsByAgeGreaterOrEqualJPQL(age);
    }

    public List<StudentData> getStudentsWithNonNullCityJPQL() {
        return studentRepository.findStudentsWithNonNullCityJPQL();
    }

    public List<StudentData> getStudentsByAgeRangeJPQL(
            int minAge,
            int maxAge) {

        return studentRepository.findStudentsByAgeRangeJPQL(
                minAge,
                maxAge
        );
    }

    public List<StudentData> getStudentsByCitiesJPQL(List<String> cities) {
        return studentRepository.findStudentsByCitiesJPQL(cities);
    }

    public List<StudentData> searchStudentsByNameJPQL(String name) {
        return studentRepository.searchStudentsByNameJPQL(name);
    }

    public List<StudentData> getStudentsSortedByAgeDescJPQL() {
        return studentRepository.findStudentsSortedByAgeDescJPQL();
    }

    public List<StudentData> getStudentsSortedByAgeJPQL() {
        return studentRepository.findStudentsSortedByAgeJPQL();
    }

    public List<StudentData> getStudentsByCityOrAgeJPQL(
            String city,
            int age) {

        return studentRepository.findByCityOrAgeJPQL(city, age);
    }

    public List<StudentData> getStudentsByCityAndAgeJPQL(
            String city,
            int age) {

        return studentRepository.findByCityAndAgeJPQL(city, age);
    }

    public long countAllStudentsJPQL() {
        return studentRepository.countAllStudentsJPQL();
    }

    public List<StudentData> findAllStudentsJPQL() {
        return studentRepository.findAllStudentsJPQL();
    }

    public int updateStudentCity(Long id, String city) {
        return studentRepository.updateStudentCity(id, city);
    }

    public int deleteStudentByIdJPQL(Long id) {
        return studentRepository.deleteStudentByIdJPQL(id);
    }

    public List<StudentData> getStudentsByCity(String city) {
        return studentRepository.findByCity(city);
    }

    public List<StudentData> getStudentsByAgeGreaterThan(int age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    public Page<StudentData> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Transactional
    public StudentData saveStudentEntity(StudentData student) {
        return studentRepository.save(student);
    }

    public StudentResponseDTO saveStudent(
            StudentRequestDTO studentRequestDTO) {

        StudentData student = new StudentData();

        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setCity(studentRequestDTO.getCity());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhone(studentRequestDTO.getPhone());

        StudentData savedStudent = studentRepository.save(student);

        return convertToResponseDTO(savedStudent);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {

        Page<StudentData> students =
                studentRepository.findAll(pageable);

        return students.map(this::convertToResponseDTO);
    }

    public StudentResponseDTO getStudentById(Long id) {

        StudentData studentData = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        ));

        return convertToResponseDTO(studentData);
    }

    public List<StudentSummaryDTO> getStudentSummaries() {
        return studentRepository.findStudentSummaries();
    }

    public StudentResponseDTO updateStudent(
            Long id,
            StudentRequestDTO studentRequestDTO) {

        StudentData existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        ));

        existingStudent.setName(studentRequestDTO.getName());
        existingStudent.setAge(studentRequestDTO.getAge());
        existingStudent.setCity(studentRequestDTO.getCity());
        existingStudent.setEmail(studentRequestDTO.getEmail());
        existingStudent.setPhone(studentRequestDTO.getPhone());

        StudentData updatedStudent =
                studentRepository.save(existingStudent);

        return convertToResponseDTO(updatedStudent);
    }

    public String deleteStudent(Long id) {

        StudentData student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        ));

        studentRepository.delete(student);

        return "Student Deleted";
    }

    @Transactional
    public void testTransaction(
            StudentData student1,
            StudentData student2) {

        studentRepository.save(student1);

        boolean fail = true;

        if (fail) {
            throw new RuntimeException("Testing rollback");
        }

        studentRepository.save(student2);
    }

    private StudentResponseDTO convertToResponseDTO(
            StudentData studentData) {

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(studentData.getId());
        response.setName(studentData.getName());
        response.setAge(studentData.getAge());
        response.setCity(studentData.getCity());
        response.setEmail(studentData.getEmail());
        response.setPhone(studentData.getPhone());

        List<CourseResponseDTO> courses = new ArrayList<>();

        if (studentData.getCourses() != null) {

            for (Course course : studentData.getCourses()) {

                CourseResponseDTO courseDTO =
                        new CourseResponseDTO();

                courseDTO.setId(course.getId());
                courseDTO.setCourseName(course.getCourseName());

                courses.add(courseDTO);
            }
        }

        response.setCourses(courses);

        return response;
    }
}