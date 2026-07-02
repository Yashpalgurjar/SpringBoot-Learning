package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentData> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentData saveStudent(StudentData student) {

        if(student.getAge() < 18) {
            throw new RuntimeException("Age must be 18+");
        }

        return studentRepository.save(student);
        
        
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
