package microservice.springcloud.student.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.service.StudentServiceImpl;

@RestController
@RequestMapping( "/student" )
public class StudentController {
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@GetMapping( "/{studentId}" )
	public ResponseEntity<?> findStudent( @PathVariable( "studentId" ) Long studentId ) {
		
		return ResponseEntity.ok().body( studentService.findById( studentId ) );
	}
}
