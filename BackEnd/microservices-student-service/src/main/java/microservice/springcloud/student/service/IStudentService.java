package microservice.springcloud.student.service;

import java.util.Optional;

import microservice.springcloud.student.model.Student;

public interface IStudentService {
	
	public Optional<Student> findById( Long studentId ) throws Exception;
	
	public Iterable<Student> findAll();
	
	public Student save( Student student );
	
	public void deleteById( Long studentId );
}
