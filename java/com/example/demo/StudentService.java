
    package com.example.demo;

    import java.util.ArrayList;
    import org.springframework.transaction.annotation.Transactional;
    import java.util.List;
    import java.util.Optional;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import com.example.demo.dto.CourseResponseDTO;
    import com.example.demo.dto.StudentRequestDTO;
    import com.example.demo.dto.StudentResponseDTO;

    @Service
    public class StudentService {

        private final StudentRepository studentRepository;

        public StudentService(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
           
            }
        @Transactional
        public StudentData saveStudent(StudentData student) {
            return studentRepository.save(student);
            
        }
        @Transactional(readOnly = true)
       
        public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {

            Page<StudentData> students =
                    studentRepository.findAll(pageable);

            return students.map(student -> {

                StudentResponseDTO dto =
                        new StudentResponseDTO();

                dto.setId(student.getId());
                dto.setName(student.getName());
                dto.setAge(student.getAge());
                dto.setCity(student.getCity());
                dto.setEmail(student.getEmail());
                dto.setPhone(student.getPhone());

                return dto;
            });
        }
               
        
       
        public List<StudentData> getStudentsByCity(String city) {

            return studentRepository.findByCity(city);
        }

        //
        public List<StudentData> getStudentsByAgeGreaterThan(int age) {

            return studentRepository.findByAgeGreaterThan(age);
        }

        
        public StudentResponseDTO saveStudent(
                StudentRequestDTO studentRequestDTO) {
        	

            StudentData student = new StudentData();

            student.setName(studentRequestDTO.getName());
            student.setAge(studentRequestDTO.getAge());
            student.setCity(studentRequestDTO.getCity());
            student.setEmail(studentRequestDTO.getEmail());
            student.setPhone(studentRequestDTO.getPhone());

            StudentData savedStudent =
                    studentRepository.save(student);

            StudentResponseDTO response =
                    new StudentResponseDTO();

            response.setId(savedStudent.getId());
            response.setName(savedStudent.getName());
            response.setAge(savedStudent.getAge());
            response.setCity(savedStudent.getCity());
            response.setEmail(savedStudent.getEmail());
            response.setPhone(savedStudent.getPhone());

            return response;
        }

        
        public StudentResponseDTO getStudentById(Long id) {

            Optional<StudentData> student =
                    studentRepository.findById(id);

            if (student.isPresent()) {

                StudentData studentData =
                        student.get();

                StudentResponseDTO response =
                        new StudentResponseDTO();

                response.setId(studentData.getId());
                response.setName(studentData.getName());
                response.setAge(studentData.getAge());
                response.setCity(studentData.getCity());
                response.setEmail(studentData.getEmail());
                response.setPhone(studentData.getPhone());

              
                List<CourseResponseDTO> courses =
                        new ArrayList<>();

                for (Course course : studentData.getCourses()) {

                    CourseResponseDTO courseDTO =
                            new CourseResponseDTO();

                    courseDTO.setId(course.getId());
                    courseDTO.setCourseName(course.getCourseName());

                    courses.add(courseDTO);
                }

                response.setCourses(courses);

                return response;

            } else {
                throw new StudentNotFoundException(
                        "Student not found with id: " + id
                );
            }            }
        

        public StudentResponseDTO updateStudent(
                Long id,
                StudentRequestDTO studentRequestDTO) {

            Optional<StudentData> studentOptional =
                    studentRepository.findById(id);

            if (studentOptional.isPresent()) {

                StudentData existingStudent =
                        studentOptional.get();

                existingStudent.setName(
                        studentRequestDTO.getName());

                existingStudent.setAge(
                        studentRequestDTO.getAge());

                existingStudent.setCity(
                        studentRequestDTO.getCity());

                existingStudent.setEmail(
                        studentRequestDTO.getEmail());

                existingStudent.setPhone(
                        studentRequestDTO.getPhone());

                StudentData updatedStudent =
                        studentRepository.save(existingStudent);

                StudentResponseDTO response =
                        new StudentResponseDTO();

                response.setId(updatedStudent.getId());
                response.setName(updatedStudent.getName());
                response.setAge(updatedStudent.getAge());
                response.setCity(updatedStudent.getCity());
                response.setEmail(updatedStudent.getEmail());
                response.setPhone(updatedStudent.getPhone());

                return response;
            } else {
                throw new StudentNotFoundException(
                        "Student not found with id: " + id);
            }            }
        
        public String deleteStudent(Long id) {

            Optional<StudentData> studentOptional =
                    studentRepository.findById(id);

            if (studentOptional.isPresent()) {

                studentRepository.deleteById(id);

                return "Student Deleted";
            }

            throw new RuntimeException(
                    "Student Not Found");
        }

        @Transactional
        public void testTransaction(StudentData student1, StudentData student2) {

            studentRepository.save(student1);

            boolean fail = true;

            if (fail) {
                throw new RuntimeException("Testing rollback");
            }

            studentRepository.save(student2);
        }
    }
