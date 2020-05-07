package microservice.springcloud.student.service;

import java.util.List;

import springcloud.microservices.commons.service.CommonService;
import springcloud.microservices.student.common.model.Student;

public interface StudentService extends CommonService<Student> {
	
	public List<Student> findByNameOrLastName( String search );
	
}
