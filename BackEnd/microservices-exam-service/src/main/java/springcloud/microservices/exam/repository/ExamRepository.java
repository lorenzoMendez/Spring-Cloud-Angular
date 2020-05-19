package springcloud.microservices.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springcloud.microservices.exam.common.model.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {
	
	@Query( "SELECT e FROM Exam e WHERE e.name like %?1% " )
	public List<Exam> findByName( String name );
	
	@Query( "SELECT e.id FROM Question q JOIN q.exam e WHERE q.questionId IN ?1 GROUP BY e.id" )
	public Iterable<Long> findExamenAnsweredByQuestionId( List<Long> ids );
}
