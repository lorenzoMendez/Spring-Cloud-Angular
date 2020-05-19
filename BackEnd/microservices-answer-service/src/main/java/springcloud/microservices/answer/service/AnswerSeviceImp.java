package springcloud.microservices.answer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import springcloud.microservices.answer.client.ExamFeignClient;
import springcloud.microservices.answer.model.Answer;
import springcloud.microservices.answer.repository.AnswerRepository;

@Service
public class AnswerSeviceImp implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	// @Autowired
	// private ExamFeignClient examClient;
	
	@Override
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		answers = ( (List<Answer>) answers ).stream().map( a -> {
			a.setStudentId( a.getStudent().getId() );
			a.setQuestionId( a.getQuestion().getQuestionId() );
			return a;
		} ).collect( Collectors.toList() );
		
		return answerRepository.saveAll( answers );
	}

	// Retorna Respuestas con estudiantes y el examen
	@Override
	public Iterable<Answer> findAnswerByStudentIdAndExamId(Long studentId, Long examId) {
		// Ahora se guardan las relaciones en mongo y podemos hace la consulta directamente
		/*Exam exam = examClient.findByExamId( examId );
		
		List<Question> questions = exam.getQuestions();
		List<Long> questionsIds = questions.stream().map( q -> q.getQuestionId() ).collect( Collectors.toList() );
		List<Answer> answers = (List<Answer>) this.answerRepository.findAnswerByStudentIdByQuestionId( studentId, questionsIds );
		
		answers = answers.stream().map( a -> {
			questions.forEach( q -> { 
				if( q.getQuestionId().compareTo( a.getQuestionId() ) == 0 ) {
					a.setQuestion( q );
				}
			} );
			return a;
		} ).collect( Collectors.toList() ); */
		
		List<Answer> answers = (List<Answer>) answerRepository.findAnswerByStudentIdByExamId( studentId, examId );
		
		return answers;
	}

	// Examenes contestados
	@Override
	public Iterable<Long> findExamenAnsweredByStudentId(Long studentId) {
		/* List<Answer> questions = (List<Answer>) answerRepository.findByStudentId( studentId );
		List<Long> examIds = Collections.emptyList();
		if( questions.size() > 0 ) {
			List<Long> questionIds = questions.stream().map( q -> q.getQuestionId() ).collect( Collectors.toList() );
			examIds = examClient.answered( questionIds );
		} */
		
		List<Answer> questions = (List<Answer>) 
			answerRepository.findExamenAnsweredByStudentId( studentId );
		
		List<Long> examIds = questions
			.stream().map( q -> q.getQuestion().getExam().getId() )
			.distinct().collect( Collectors.toList() );
		
		return examIds;
	}

	@Override
	public Iterable<Answer> findByStudentId(Long studentId) {
		
		return this.answerRepository.findByStudentId( studentId );
	}

}
