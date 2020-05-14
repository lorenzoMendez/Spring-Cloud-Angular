package springcloud.microservices.exam.service;

import java.util.List;

import springcloud.microservices.commons.service.CommonService;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.exam.common.model.Subject;

public interface ExamService extends CommonService<Exam> {
	
	public List<Exam> findByName( String exam );
	
	public Iterable<Subject> findAllSubject();
	
	public Iterable<Long> findExamenAnsweredByQuestionId( List<Long> ids );
}
