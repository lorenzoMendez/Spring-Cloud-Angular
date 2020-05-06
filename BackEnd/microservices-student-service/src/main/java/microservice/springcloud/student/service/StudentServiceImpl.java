package microservice.springcloud.student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.springcloud.student.model.Student;
import microservice.springcloud.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional( readOnly = true )
	public Optional<Student> findById(Long studentId) {
		
		return studentRepository.findById( studentId );
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<Student> findAll() {

		return studentRepository.findAll();
	}

	@Override
	@Transactional
	public Student save(Student student) {
		
		return studentRepository.save( student );
	}

	@Override
	@Transactional
	public void deleteById(Long studentId) {

		studentRepository.deleteById( studentId );
	}
}
