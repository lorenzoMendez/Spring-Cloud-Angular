package springcloud.microservices.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springcloud.microservices.answer.model.Answer;
import springcloud.microservices.answer.service.AnswerService;

@RestController
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	// Guardar las respuestaas de un examen
	@PostMapping( "/save-answers" )
	public ResponseEntity<?> saveAnswers( @RequestBody Iterable<Answer> answers ) {
		try {
			
			return ResponseEntity.status( HttpStatus.CREATED ).body( answerService.saveAll( answers ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( err.getMessage() );
		}
	}
	
	@GetMapping( "/student/{studentId}/exam/{examId}" )
	public ResponseEntity<?> findAnswersByStudentExam( @PathVariable( "studentId" ) Long studentId, @PathVariable( "examId" ) Long examId ) {
		try {
			
			return ResponseEntity.ok( answerService.findAnswerByStudentIdAndExamId(studentId, examId) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( err.getMessage() );
		}
	}
	
	@GetMapping( "/student/{studentId}/exam-answered" )
	public ResponseEntity<?> findExamAnswered( @PathVariable( "studentId" ) Long studentId ) {
		try {
			
			return ResponseEntity.ok( answerService.findExamenAnsweredByStudentId( studentId ) );
			
		} catch( Exception err ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( err.getMessage() );
		}
	}
}
