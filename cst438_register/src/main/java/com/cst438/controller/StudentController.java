package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.Enrollment;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin (origins = {"http://localhost:3000"})
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/student")
	public StudentDTO createNewStudent(@RequestBody StudentDTO StudentDTO) {
				
		if (StudentDTO.name!= null && StudentDTO.email!=null && !studentRepository.existsByEmail(StudentDTO.email) ) {
		Student inputStudent = new Student();
		inputStudent.setName(StudentDTO.name);
		inputStudent.setEmail(StudentDTO.email);
		inputStudent.setStatusCode(StudentDTO.statusCode);
		Student savedStudent = studentRepository.save(inputStudent);
		
		StudentDTO result = createStudentDTO(savedStudent);
		
		return result;
		}
		
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student Name or Email Invalid, or Email Already exists in system ");
		}
	}
	
	@PutMapping("/student")
	@Transactional
	public void updateHoldStatus(@RequestParam("email") String student_email, @RequestParam("status") int status) {
		
		Student student = studentRepository.findByEmail(student_email);
		student.setStatusCode(status);
		studentRepository.save(student);
	}
	
	
	private StudentDTO createStudentDTO(Student S) {
		
		StudentDTO StudentDTO = new StudentDTO();
		
		StudentDTO.student_id = S.getStudent_id();
		StudentDTO.name = S.getName();
		StudentDTO.email = S.getEmail();
		StudentDTO.statusCode = S.getStatusCode();
		StudentDTO.status = S.getStatus();

		
		return StudentDTO;
	}
}