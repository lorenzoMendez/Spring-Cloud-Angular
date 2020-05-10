package springcloud.microservices.course.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springcloud.microservices.commons.controller.CommonController;
import springcloud.microservices.course.model.Course;
import springcloud.microservices.course.model.service.CourseServiceImpl;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.student.common.model.Student;

@RestController
public class CourseController extends CommonController<Course, CourseServiceImpl> {
	
	@PutMapping( "/update" )
	public ResponseEntity<?> updateCourse( @Valid @RequestBody Course course, BindingResult result ) {
		
		if( result.hasErrors() ) {
			return validation( result );
		}
		
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.update( course ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( "Error! No se pudo actualizar el curso" );
		}
	}
	
	@PutMapping( "/{courseId}/register-student" )
	public ResponseEntity<?> registerStudent( @RequestBody List<Student> listStudent, @PathVariable( "courseId" ) Long courseId ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.registerStudent(listStudent, courseId) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( "Error! No se pudo actualizar el curso" );
		}
	}
	
	@PutMapping( "/{courseId}/delete-student" )
	public ResponseEntity<?> deleteStudentFromCourse( @RequestBody Student student, @PathVariable( "courseId" ) Long courseId ) {
		try {
			
			return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( this.service.deleteStudentFromCourse(student, courseId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( "Error! No se pudo actualizar el curso" );
		}
	}
	
	@GetMapping( "/student/{studentId}" )
	public ResponseEntity<?> findByStudentId( @PathVariable( "studentId" ) Long studentId ) {
		try {
			
			return ResponseEntity.ok( this.service.findCourseByStudentId( studentId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( err.getMessage() );
		}
	}
	
	@PutMapping( "/{courseId}/register-exam" )
	public ResponseEntity<?> registerExam( @RequestBody List<Exam> listExam, @PathVariable( "courseId" ) Long courseId ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( this.service.registerExam( listExam, courseId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( "Error! No se pudo actualizar el curso" );
		}
	}
	
	@PutMapping( "/{courseId}/delete-exam" )
	public ResponseEntity<?> deleteExamtFromCourse( @RequestBody Exam exam, @PathVariable( "courseId" ) Long courseId ) {
		try {
			
			return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( this.service.deleteExamFromCourse( exam, courseId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_MODIFIED ).body( "Error! No se pudo actualizar el curso" );
		}
	}
}
