package microservice.springcloud.student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.repository.StudentRepository;
import microservice.springcloud.student.utils.MailUtils;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional( readOnly = true )
	public Optional<Student> findById(Long studentId) throws Exception {
		
		Optional<Student> student = studentRepository.findById( studentId );
		if( !student.isPresent() ) {
			throw new Exception( "Estudiante con id " + studentId + " no existe." );
		}
		return student;
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Student> findAll() {
		
		return studentRepository.findAll();
	}

	@Override
	@Transactional
	public Student save(Student student) throws Exception {
		
		if( !MailUtils.validateEmail( student.getEmail() ) ) {
			throw new Exception( "Formato de correo electónico incorrecto." );
		}
		
		return studentRepository.save( student );
	}
	
	@Transactional
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
	@Transactional
	public void deleteById(Long studentId) {
		
		studentRepository.deleteById( studentId );
	}
}
