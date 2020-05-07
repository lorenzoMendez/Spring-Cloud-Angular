package springcloud.microservices.course.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.course.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	
	@Query( "SELECT c FROM Course c JOIN FETCH c.students s WHERE s.studentId=?1" )
	public Course findCourseByStudentId( Long studentId );
}
