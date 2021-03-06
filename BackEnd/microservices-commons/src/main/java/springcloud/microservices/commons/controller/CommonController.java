package springcloud.microservices.commons.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springcloud.microservices.commons.service.CommonService;

// @CrossOrigin( { "http://localhost:4200" } )
public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping( "/pagination" )
	public ResponseEntity<?> findAll( Pageable pageable ) {
		try {
			
			return ResponseEntity.ok( service.findAll( pageable ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "Error al recuperar la lista." );
		}
	}
	
	@GetMapping( "" )
	public ResponseEntity<?> findAll() {
		try {
			
			return ResponseEntity.ok( service.findAll() );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "Error al recuperar la lista." );
		}
	}
	
	@GetMapping( "/{id}" )
	public ResponseEntity<?> findById( @PathVariable( "id" ) Long id ) {
		try {
			
			return ResponseEntity.ok().body( service.findById( id ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND).body( err.getMessage() );
		}
	}
	
	@PostMapping( "" )
	public ResponseEntity<?> save( @Valid @RequestBody E entity, BindingResult result ) {
		
		if( result.hasErrors() ) {
			return validation( result );
		}
		
		return ResponseEntity.status( HttpStatus.CREATED ).body( service.save( entity ) );
	}
	
	@DeleteMapping( "/{id}" )
	public ResponseEntity<?> delete( @PathVariable( "id" ) Long id ) {
		try {
			
			service.deleteById( id );
			
			return ResponseEntity.status( HttpStatus.CREATED ).build();
		
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al eliminar el registro con id " + id + "." );
		}
	}
	
	protected ResponseEntity<?> validation( BindingResult result ) {
		
		Map<String, Object> errors = new HashMap<String, Object>();
		result.getFieldErrors().forEach( err -> {
			errors.put( err.getField(), err.getDefaultMessage() );
		} );
		
		return ResponseEntity.badRequest().body( errors );
	}
}
