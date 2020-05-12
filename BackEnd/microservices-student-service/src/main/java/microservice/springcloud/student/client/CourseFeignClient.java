package microservice.springcloud.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "microservice-course-service" )
public interface CourseFeignClient {
	
	@DeleteMapping( "/delete-student/{studentId}" )
	public void deleteCourseByStudentId( @PathVariable( "studentId" ) Long studentId );
}
