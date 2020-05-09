package microservice.springcloud.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import springcloud.microservices.student.common.model.Student;
import microservice.springcloud.student.repository.StudentRepository;
import springcloud.microservices.commons.service.CommonServiceImpl;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService {
	
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

	@Override
	@Transactional( readOnly = true )
	public List<Student> findByNameOrLastName(String search) {
		
		return this.repository.findByNameOrLastName( search );
	}
	
	@Transactional
	public Student saveWithPhoto( Student student, MultipartFile file ) throws Exception {
		if( !file.isEmpty() ) {
			student.setPhoto( file.getBytes() );
		}
		return this.repository.save( student );
	}
	
	public Student updateWithPhoto( Student student, MultipartFile file ) throws Exception {
		
		if( student.getStudentId() == null ) {
			throw new Exception( "El id del alumno es obligatorio." );
		}
		
		Optional<Student> oldStudent = this.findById( student.getStudentId() );
		
		oldStudent.get().setEmail( student.getEmail() );
		oldStudent.get().setName( student.getName() );
		oldStudent.get().setLastName( student.getLastName() );
		
		if( !file.isEmpty() ) {
			oldStudent.get().setPhoto( file.getBytes() );
		}
		
		return this.save( oldStudent.get() );
	}
}
