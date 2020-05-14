package springcloud.microservices.answer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import springcloud.microservices.answer.model.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String> {
	
	@Query( "{ 'studentId': ?0, 'questionId': { $in: ?1 } }" )
	public Iterable<Answer> findAnswerByStudentIdByQuestionId( Long studentId, List<Long> questionsId );
	
	@Query( "{ 'studentId': ?0 }" )
	public Iterable<Answer> findByStudentId( Long studentId );
	
	@Query( "{ 'studentId': ?0, 'question.exam.examId': ?1 }" )
	public Iterable<Answer> findAnswerByStudentIdByExamId( Long studentId, Long examId );
	
	@Query( value = "{ 'studentId': ?0 }", fields = "{ 'question.exam.examId': 1 }" )
	public Iterable<Answer> findExamenAnsweredByStudentId( Long studentId );
	
	// @Query( "SELECT a FROM Answer a JOIN FETCH a.question q JOIN FETCH q.exam e WHERE a.studentId = ?1 and e.examId = ?2" )
	// public Iterable<Answer> findAnswerByStudentIdByExamId( Long studentId, Long examId );
	
	// @Query( "SELECT e.examId FROM Answer a JOIN a.question q JOIN q.exam e WHERE a.studentId = ?1 GROUP BY e.examId" )
	// public Iterable<Long> findExamenAnsweredByStudentId( Long studentId );
}
