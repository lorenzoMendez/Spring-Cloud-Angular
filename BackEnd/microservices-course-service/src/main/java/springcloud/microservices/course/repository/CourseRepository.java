package springcloud.microservices.course.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springcloud.microservices.course.model.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
	
	@Query( "SELECT c FROM Course c JOIN FETCH c.courseStudents s WHERE s.studentId = ?1" )
	public Course findCourseByStudentId( Long studentId );
	
	@Modifying
	@Query( "DELETE FROM CourseStudent cs WHERE cs.studentId = ?1" )
	public void deleteCourseByStudentId( Long studentId );
}