package springcloud.microservices.course.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springcloud.microservices.commons.service.CommonServiceImpl;
import springcloud.microservices.course.model.Course;
import springcloud.microservices.course.repository.CourseRepository;
import springcloud.microservices.exam.common.model.Exam;
import springcloud.microservices.student.common.model.Student;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {
	
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
	public Course findCourseByStudentId(Long studentId) {
		
		return repository.findCourseByStudentId( studentId );
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
}