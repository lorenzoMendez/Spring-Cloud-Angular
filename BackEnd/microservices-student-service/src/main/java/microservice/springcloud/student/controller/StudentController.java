package microservice.springcloud.student.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springcloud.microservices.student.common.model.Student;
import microservice.springcloud.student.service.StudentServiceImpl;
import springcloud.microservices.commons.controller.CommonController;

@RestController
public class StudentController extends CommonController<Student, StudentServiceImpl> {
	
	@PutMapping( "/update" )
	public ResponseEntity<?> updateStudent( @Valid @RequestBody Student student, BindingResult result ) {
		
		if( result.hasErrors() ) {
			return validation( result );
		}
		try {
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.update( student ) );
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( err.getMessage() );
		}
	}
	
	@GetMapping( "/search/{search}" )
	public ResponseEntity<?> searchStudent( @PathVariable( "search" ) String search ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.findByNameOrLastName(search) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Error al recuperar los usuarios." );
		}
	}
}
