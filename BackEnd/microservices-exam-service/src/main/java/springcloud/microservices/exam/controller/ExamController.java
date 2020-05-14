package springcloud.microservices.exam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springcloud.microservices.commons.controller.CommonController;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.exam.service.ExamServiceImpl;

@RestController
public class ExamController extends CommonController<Exam, ExamServiceImpl> {
	
	@GetMapping( "/answered" )
	public ResponseEntity<?> answered( @RequestParam List<Long> questionIds ) {
		try {
			
			return ResponseEntity.ok( this.service.findExamenAnsweredByQuestionId( questionIds ) );
		
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "No se encontraron registros." );
		}
	}
	
	@PutMapping( "/update" )
	public ResponseEntity<?> update( @Valid @RequestBody Exam exam, BindingResult result ) {
		
		if( result.hasErrors() ) {
			return validation( result );
		}
		
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.update( exam ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( err.getMessage() );
		}
	}
	
	@GetMapping( "/filter/{name}" )
	public ResponseEntity<?> filterByName( @PathVariable( "name" ) String name ) {
		try {
			
			return ResponseEntity.status( HttpStatus.OK ).body( this.service.findByName( name ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "No se encontraron registros." );
		}
	}
	
	@GetMapping( "/subject" )
	public ResponseEntity<?> findSubject() {
		try {
			
			return ResponseEntity.status( HttpStatus.OK ).body( this.service.findAllSubject() );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "No se encontraron registros." );
		}
	}
	
}
