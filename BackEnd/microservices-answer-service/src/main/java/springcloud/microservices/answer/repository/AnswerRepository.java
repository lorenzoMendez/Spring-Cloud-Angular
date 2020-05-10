package springcloud.microservices.answer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.answer.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	@Query( "SELECT a FROM Answer a JOIN FETCH a.student s JOIN FETCH a.question "
			+ "q JOIN FETCH q.exam e WHERE s.studentId = ?1 and e.examId = ?2" )
	public Iterable<Answer> findAnswerByStudentIdByExamId( Long studentId, Long examId );
	
	@Query( "SELECT e.examId FROM Answer a JOIN a.student s JOIN a.question "
			+ "q JOIN q.exam e WHERE s.studentId = ?1 GROUP BY e.examId" )
	public Iterable<Long> findExamenAnsweredByStudentId( Long studentId );
}
