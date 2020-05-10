package springcloud.microservices.course.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springcloud.microservices.commons.service.CommonServiceImpl;
import springcloud.microservices.course.client.AnswerFeignClient;
import springcloud.microservices.course.model.Course;
import springcloud.microservices.course.repository.CourseRepository;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.student.common.model.Student;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {
	
	@Autowired
	private AnswerFeignClient answerFeignClient;
	
	public Course update( Course course ) throws Exception {
		
		if( course.getCourseId() == null ) {
			throw new Exception( "El id del alumno es obligatorio." );
		}
		
		Optional<Course> oldCourse = this.findById( course.getCourseId() );
		
		oldCourse.get().setDescription( course.getDescription() );
		oldCourse.get().setActiveId( course.getActiveId() );
		
		return this.save( oldCourse.get() );
	}
	
	public Course registerStudent( List<Student> listStudent, Long courseId ) throws Exception {
		
		Optional<Course> course = this.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		listStudent.forEach( dbCourse::addStudentList );
		
		return this.save( dbCourse );
	}
	
	public Course deleteStudentFromCourse( Student student, Long courseId ) throws Exception {
		
		Optional<Course> course = this.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		dbCourse.removeStudentList( student );
		
		return this.save( dbCourse );
	}

	@Override
	@Transactional( readOnly = true )
	public Course findCourseByStudentId(Long studentId) throws Exception {
		
		Course course = repository.findCourseByStudentId( studentId );
		
		if( course == null ) {
			throw new Exception( "No se encontro el curso con el identificador de estudiante " + studentId );
			
		}
		
		List<Long> examsId = (List<Long>) findExamAnswered( studentId );
		
		List<Exam> exams = course.getExams().stream().map( exam -> { 
			if( examsId.contains( exam.getExamId() ) ) {
				exam.setAnswerStatus( true );
			}
			return exam;
		} ).collect( Collectors.toList() );
		
		course.setExams( exams );
		
		return course;
	}
	
	public Course registerExam( List<Exam> listExam, Long courseId ) throws Exception {
		
		Optional<Course> course = this.findById( courseId );
		
		if( !course.isPresent() ) {
			throw new Exception( "El curso con id " + courseId + " no existe" );
		}
		Course dbCourse = course.get();
		
		listExam.forEach( dbCourse::addExam );
		
		return this.save( dbCourse );
	}
	
	public Course deleteExamFromCourse( Exam exam, Long courseId ) throws Exception {
		
		Optional<Course> course = this.findById( courseId );
		
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
}