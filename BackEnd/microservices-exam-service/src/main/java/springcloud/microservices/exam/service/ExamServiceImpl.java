package springcloud.microservices.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import springcloud.microservices.commons.service.CommonServiceImpl;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.exam.common.model.Question;
import springcloud.microservices.exam.repository.ExamRepository;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository > implements ExamService {
	
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
}
