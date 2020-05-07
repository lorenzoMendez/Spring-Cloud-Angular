package springcloud.microservices.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springcloud.microservices.commons.service.ICommonService;

public class CommonController<E, S extends ICommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping( "/retrieve" )
	public ResponseEntity<?> findStudents() {
		try {
			
			return ResponseEntity.ok( service.findAll() );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "Error al recuperar el listado de alumnos." );
		}
	}
	
	@GetMapping( "/retrieve/{studentId}" )
	public ResponseEntity<?> findStudent( @PathVariable( "studentId" ) Long id ) {
		try {
			
			return ResponseEntity.ok().body( service.findById( id ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND).body( err.getMessage() );
		}
	}
	
	@PostMapping( "/save" )
	public ResponseEntity<?> saveStudent( @RequestBody E entity ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( service.save( entity ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( err.getMessage() );
		}
	}
	
	@DeleteMapping( "/delete/{studentId}" )
	public ResponseEntity<?> deleteStudent( @PathVariable( "studentId" ) Long studentId ) {
		try {
			
			service.deleteById( studentId );
			
			return ResponseEntity.status( HttpStatus.CREATED ).build();
		
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al eliminar el estudiante." );
		}
	}
}
