package microservice.springcloud.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.student.common.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Query( "SELECT u FROM Student u WHERE u.name LIKE %?1% OR u.lastName LIKE %?1%" )
	public List<Student> findByNameOrLastName( String search );
}
