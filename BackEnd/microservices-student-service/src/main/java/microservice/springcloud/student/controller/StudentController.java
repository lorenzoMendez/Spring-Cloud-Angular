package microservice.springcloud.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.service.StudentServiceImpl;

@RestController
@RequestMapping( "/student" )
public class StudentController {
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@GetMapping( "/retrieve" )
	public ResponseEntity<?> findStudents() {
		try {
			
			return ResponseEntity.ok( studentService.findAll() );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "Error al recuperar el listado de alumnos." );
		}
	}
	
	@GetMapping( "/retrieve/{studentId}" )
	public ResponseEntity<?> findStudent( @PathVariable( "studentId" ) Long studentId ) {
		try {
			
			return ResponseEntity.ok().body( studentService.findById( studentId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND).body( err.getMessage() );
		}
	}
	
	@PostMapping( "/save" )
	public ResponseEntity<?> saveStudent( @RequestBody Student student ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( studentService.save(student) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( err.getMessage() );
		}
	}
	
	@PutMapping( "/update" )
	public ResponseEntity<?> updateStudent( @RequestBody Student student ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( studentService.update( student ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( err.getMessage() );
		}
	}
	
	@DeleteMapping( "/delete/{studentId}" )
	public ResponseEntity<?> deleteStudent( @PathVariable( "studentId" ) Long studentId ) {
		try {
			return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al eliminar el estudiante." );
		}
	}
}
