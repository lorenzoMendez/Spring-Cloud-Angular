package microservice.springcloud.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import springcloud.microservices.student.common.model.Student;
import microservice.springcloud.student.client.CourseFeignClient;
import microservice.springcloud.student.repository.StudentRepository;
import springcloud.microservices.commons.service.CommonServiceImpl;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService {
	
	@Autowired
	private CourseFeignClient courseClient;
	
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
	
	public Resource retrievePhoto( Long studentId ) throws Exception {
		Optional<Student> student = this.findById( studentId );
		if( !student.isPresent() ) {
			throw new Exception( "Alumno con id " + studentId + " no existe." );
		}
		
		if( student.get().getPhoto() == null ) {
			throw new Exception( "Alumno con id " + studentId + " no tiene una foto."  );
		}
		
		return new ByteArrayResource( student.get().getPhoto() );
	}

	@Override
	@Transactional( readOnly = true )
	public List<Student> findAllByIds( List<Long> list) {
		if( list.size() > 0 ) {
			return (List<Student>) this.repository.findAllById( list );
		}
		return new ArrayList<>();
	}

	@Override
	public void deleteCourseByStudentId(Long studentId) {
		courseClient.deleteCourseByStudentId( studentId );
	}

	@Override
	@Transactional
	public void deleteById( Long studentId ) throws Exception {
		super.deleteById( studentId );
		this.deleteCourseByStudentId( studentId );
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Student> findAll() {
		
		return this.repository.findAllByOrderByStudentIdAsc();
	}

	@Override
	@Transactional( readOnly = true )
	public Page<Student> findAll(Pageable pageable) {
		
		return this.repository.findAllByOrderByStudentIdAsc( pageable );
	}	
}
