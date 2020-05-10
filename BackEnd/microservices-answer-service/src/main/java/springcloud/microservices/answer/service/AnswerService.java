package springcloud.microservices.answer.service;

import springcloud.microservices.answer.model.Answer;

public interface AnswerService {
	
	public Iterable<Answer> saveAll(  Iterable<Answer> answers );
	
	public Iterable<Answer> findAnswerByStudentIdByExamId( Long studentId, Long examId );
	
	public Iterable<Long> findExamenAnsweredByStudentId( Long studentId );
}
