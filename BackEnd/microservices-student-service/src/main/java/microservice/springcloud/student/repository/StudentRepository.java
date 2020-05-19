package microservice.springcloud.student.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springcloud.microservices.student.common.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	@Query( "SELECT u FROM Student u WHERE UPPER( u.name ) LIKE UPPER( CONCAT( '%', ?1, '%' ) ) OR UPPER( u.lastName ) LIKE UPPER( CONCAT( '%', ?1, '%' ) )" )
	public List<Student> findByNameOrLastName( String search );
	
	public List<Student> findAllByOrderByIdAsc();
	
	public Page<Student> findAllByOrderByIdAsc( Pageable pageable );
}
