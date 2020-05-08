package springcloud.microservices.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springcloud.microservices.commons.controller.CommonController;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.exam.service.ExamServiceImpl;

@RestController
public class ExamController extends CommonController<Exam, ExamServiceImpl> {
	
	@PutMapping( "/update" )
	public ResponseEntity<?> update( @RequestBody Exam exam ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.update( exam ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( err.getMessage() );
		}
	}
}
