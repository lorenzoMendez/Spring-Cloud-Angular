package microservice.springcloud.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.service.StudentService;
import springcloud.microservices.commons.controller.CommonController;

@RestController
public class StudentController extends CommonController<Student, StudentService> {
	
	@PutMapping( "/update" )
	public ResponseEntity<?> updateStudent( @RequestBody Student student ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.update( student ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( err.getMessage() );
		}
	}
}
