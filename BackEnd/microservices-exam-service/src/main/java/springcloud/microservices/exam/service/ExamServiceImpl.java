package springcloud.microservices.exam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springcloud.microservices.commons.service.CommonServiceImpl;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.exam.common.model.Question;
import springcloud.microservices.exam.common.model.Subject;
import springcloud.microservices.exam.repository.ExamRepository;
import springcloud.microservices.exam.repository.SubjectRepository;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository > implements ExamService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Exam update( Exam exam ) throws Exception {
		
		if( exam.getExamId() == null ) {
			throw new Exception( "Error! El identificador del registro es nulo." );
		}
		
		Optional<Exam> dbTest = this.repository.findById( exam.getExamId() );
		
		if( !dbTest.isPresent() ) {
			throw new Exception( "Error! No existe el examen con id " + exam.getExamId() );
		}
		
		Exam update = dbTest.get();
		update.setDescription( exam.getDescription() );
		
		List<Question> deleteList =  update.getQuestions().stream()
		.filter( q -> !exam.getQuestions().contains( q ) )
		.collect( Collectors.toList() );
		
		deleteList.forEach( update::removeQuestion );
		
		update.setQuestions( exam.getQuestions() );
		
		return this.repository.save( update );
	}

	@Override
	@Transactional( readOnly = true )
	public List<Exam> findByName( String name ) {
		
		return repository.findByName( name );
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Subject> findAllSubject() {
		
		return subjectRepository.findAll();
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Long> findExamenAnsweredByQuestionId( List<Long> ids ) {
		
		if( ids != null && ids.size() > 0 ) {
			return repository.findExamenAnsweredByQuestionId( ids );
		}
		
		return new ArrayList<>();
	}
}
