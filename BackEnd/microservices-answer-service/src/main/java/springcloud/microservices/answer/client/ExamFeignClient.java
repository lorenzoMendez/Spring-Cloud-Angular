package springcloud.microservices.answer.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import springcloud.microservices.exam.common.model.Exam;

@FeignClient( name = "microservice-exam-service" )
public interface ExamFeignClient {
	
	@GetMapping( "/{id}" )
	public Exam findByExamId( @PathVariable( "id" ) Long examId );
	
	// Examenes contestados
	@GetMapping( "/answered" )
	public List<Long> answered( @RequestParam List<Long> questionIds );

}
