package microservice.springcloud.student.service;

import java.util.Optional;

import antlr.collections.List;
import microservice.springcloud.student.model.Student;

public interface IStudentService {
	
	public Optional<Student> findById( Long studentId );
	
	public Iterable<Student> findAll();
	
	public Student save( Student student );
	
	public void deleteById( Long studentId );
}
