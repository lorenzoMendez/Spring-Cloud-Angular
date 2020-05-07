package springcloud.microservices.course.model.service;

import springcloud.microservices.commons.service.CommonService;
import springcloud.microservices.course.model.Course;

public interface CourseService extends CommonService<Course> {
	
	public Course findCourseByStudentId( Long studentId );
}
