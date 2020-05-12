package springcloud.microservices.course.model.service;

import java.util.List;

import springcloud.microservices.commons.service.CommonService;
import springcloud.microservices.course.model.Course;
import springcloud.microservices.student.common.model.Student;

public interface CourseService extends CommonService<Course> {
	
	public Course findCourseByStudentId( Long studentId ) throws Exception;
	
	public Iterable<Long> findExamAnswered( Long studentId );
	
	public List<Student> retrieveStudentsByIds( List<Long> list );
	
	public void deleteCourseByStudentId( Long studentId );
}
