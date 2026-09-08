package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getAllStudents() {

        List<StudentData> students = studentRepository.findAll();

        List<StudentResponseDTO> response = new ArrayList<>();

        for (StudentData student : students) {

            StudentResponseDTO dto = new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setAge(student.getAge());
            dto.setCity(student.getCity());
            dto.setEmail(student.getEmail());
            dto.setPhone(student.getPhone());

            response.add(dto);
        }

        return response;
    }    	    
    	
    	
    public StudentData getStudentById(Long id) {
    	
    	studentRepository.findById(id);
    	Optional<StudentData> student =
    	        studentRepository.findById(id);
    	
    	if (student.isPresent()) {

    		
    		return student.get();
    		

    }else {
    	throw new RuntimeException ("Student not Found");
    }
    
    }
    public StudentData updateStudent(
            Long id,
            StudentData student) {
    	


Optional<StudentData> studentOptional =
studentRepository.findById(id);
if (studentOptional.isPresent()) {
	
    StudentData existingStudent =
            studentOptional.get();


	existingStudent.setName(
	        student.getName());

	existingStudent.setAge(
	        student.getAge());

return studentRepository.save(
        existingStudent);




}else {
	
 	throw new RuntimeException ("  Student Not Found");
}
}
    public String deleteStudent(Long id) {
    	
    	Optional<StudentData> studentOptional =
    	        studentRepository.findById(id);

    	if(studentOptional.isPresent()) {

    	    studentRepository.deleteById(id);

    	    return "Student Deleted";
    	}

    	throw new RuntimeException(
    	        "Student Not Found");
    }
}
