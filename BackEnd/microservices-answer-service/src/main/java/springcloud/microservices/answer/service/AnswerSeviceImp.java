package springcloud.microservices.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springcloud.microservices.answer.model.Answer;
import springcloud.microservices.answer.repository.AnswerRepository;

@Service
public class AnswerSeviceImp implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		
		return answerRepository.saveAll( answers );
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Answer> findAnswerByStudentIdByExamId(Long studentId, Long examId) {
		
		return answerRepository.findAnswerByStudentIdByExamId( studentId, examId );
	}

	// Examenes contestados
	@Override
	@Transactional( readOnly = true )
	public Iterable<Long> findExamenAnsweredByStudentId(Long studentId) {
		
		return answerRepository.findExamenAnsweredByStudentId( studentId );
	}

}