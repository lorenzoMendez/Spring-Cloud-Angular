package springcloud.microservices.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign ya implementa Balanceo de Carga en caso de contar con varias instancias
// Utiliza Ribbon para el balanceo de carga y asi obtiene la mejor instancia

// Especificar nombre del microservice que se consumira
@FeignClient( name = "microservice-answer-service" )
public interface AnswerFeignClient {
	
	// El metodo debe ser igual al servicio que se consume
	@GetMapping( "/student/{studentId}/exam-answered" )
	public Iterable<Long> findExamAnswered( @PathVariable( "studentId" ) Long studentId );
	
}
