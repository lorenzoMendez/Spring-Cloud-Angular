package springcloud.microservices.course.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springcloud.microservices.commons.service.CommonServiceImpl;
import springcloud.microservices.course.client.AnswerFeignClient;
import springcloud.microservices.course.client.StudentFeignClient;
import springcloud.microservices.course.model.Course;
import springcloud.microservices.course.model.CourseStudent;
import springcloud.microservices.course.repository.CourseRepository;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.student.common.model.Student;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {
	
	@Autowired
	private AnswerFeignClient answerFeignClient;
	
	@Autowired
	private StudentFeignClient studentClient;
	
	public Page<Course> findAll( Pageable pageable ) {
		
		Page<Course> pagination = this.repository.findAll( pageable ).map( course -> {
			course.getCourseStudents().forEach( cs -> {
				Student student = new Student();
				student.setId( cs.getStudentId() );
				course.addStudent( student );
			} );
			return course;
		} );
		
		return pagination;
	}
	
	public Course findDetailCourseById( Long courseId ) throws Exception {
		
		Optional<Course> optionaCourse = this.repository.findById( courseId );
		
		Course course = optionaCourse.get();
		
		List<Long> ids = course.getCourseStudents().parallelStream().map(
				cs -> cs.getStudentId() ).collect( Collectors.toList() );
		
		List<Student> students = this.retrieveStudentsByIds( ids );
		
		course.setStudents( students );
		
		return course;
		
	}

	public List<Course> retrieveAll() {
		List<Course> courses = ( (List<Course>) this.repository.findAll() )
			.stream().map( c -> {
				c.getCourseStudents().forEach( cs -> {
					Student student = new Student();
					student.setId( cs.getStudentId() );
					c.addStudent( student );
				} );
				return c;
			} ).collect( Collectors.toList() );
		
		return courses;
	}
	
	public Course update( Course course ) throws Exception {
		
		if( course.getId() == null ) {
			throw new Exception( "El id del alumno es obligatorio." );
		}
		
		Optional<Course> oldCourse = this.repository.findById( course.getId() );
		
		oldCourse.get().setName( course.getName() );
		oldCourse.get().setActiveId( course.getActiveId() );
		
		return this.save( oldCourse.get() );
	}
	
	public Course registerStudent( List<Student> listStudent, Long courseId ) throws Exception {
		
		Optional<Course> course = this.repository.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		listStudent.forEach( student -> {
			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setStudentId( student.getId() );
			courseStudent.setCourse( dbCourse );
			dbCourse.addCourseStudent( courseStudent );
		} );
		
		return this.save( dbCourse );
	}
	
	public Course deleteStudentFromCourse( Student student, Long courseId ) throws Exception {
		
		Optional<Course> course = this.repository.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		CourseStudent courseStudent = new CourseStudent();
		courseStudent.setStudentId( student.getId() );
		dbCourse.removeCourseStudent( courseStudent );
		
		return this.save( dbCourse );
	}

	@Override
	@Transactional( readOnly = true )
	public Course findCourseByStudentId(Long studentId) throws Exception {
		
		Course course = this.repository.findCourseByStudentId( studentId );
		
		if( course == null ) {
			throw new Exception( "No se encontro el curso con el identificador de estudiante " + studentId );
			
		}
		
		List<Long> examsId = (List<Long>) findExamAnswered( studentId );
		
		if( examsId != null && examsId.size() > 0 ) {
			List<Exam> exams = course.getExams().stream().map( exam -> { 
				if( examsId.contains( exam.getId() ) ) {
					exam.setAnswerStatus( true );
				}
				return exam;
			} ).collect( Collectors.toList() );
			
			course.setExams( exams );
		}
		
		return course;
	}
	
	public Course registerExam( List<Exam> listExam, Long courseId ) throws Exception {
		
		Optional<Course> course = this.repository.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		listExam.forEach( dbCourse::addExam );
		
		return this.save( dbCourse );
	}
	
	public Course deleteExamFromCourse( Exam exam, Long courseId ) throws Exception {
		
		Optional<Course> course = this.repository.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		dbCourse.removeExam( exam );
		
		return this.save( dbCourse );
	}

	@Override
	public Iterable<Long> findExamAnswered( Long studentId ) {
		
		return answerFeignClient.findExamAnswered( studentId );
	}
	
	@Override
	public List<Student> retrieveStudentsByIds(List<Long> list) {
		
		return studentClient.retrieveStudentsByIds( list );
		
	}

	@Override
	@Transactional
	public void deleteCourseByStudentId(Long studentId) {
		
		this.repository.deleteCourseByStudentId( studentId );
	}
}