package springcloud.microservices.course.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springcloud.microservices.student.common.model.Student;

@FeignClient( name = "microservice-student-service" )
public interface StudentFeignClient {
	
	@GetMapping( "/students-ids" )
	public List<Student> retrieveStudentsByIds( @RequestParam List<Long> list );
}
