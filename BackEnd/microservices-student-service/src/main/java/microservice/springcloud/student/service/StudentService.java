package microservice.springcloud.student.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.repository.StudentRepository;
import springcloud.microservices.commons.service.CommonServiceImpl;

@Service
public class StudentService extends CommonServiceImpl<Student, StudentRepository> implements IStudentService {
	
	public Student update( Student student ) throws Exception {
		
		if( student.getStudentId() == null ) {
			throw new Exception( "El id del alumno es obligatorio." );
		}
		
		Optional<Student> oldStudent = this.findById( student.getStudentId() );
		
		oldStudent.get().setEmail( student.getEmail() );
		oldStudent.get().setName( student.getName() );
		oldStudent.get().setLastName( student.getLastName() );
		
		return this.save( oldStudent.get() );
	}
}
