package microservice.springcloud.student.repository;

import org.springframework.data.repository.CrudRepository;

import microservice.springcloud.student.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
}
