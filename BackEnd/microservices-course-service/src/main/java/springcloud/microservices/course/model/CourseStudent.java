package springcloud.microservices.course.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "course_student" )
public class CourseStudent implements Serializable {
	
	private static final long serialVersionUID = -2741784933353363961L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "course_student_id" )
	private Long courseStudentId;
	
	@JsonIgnoreProperties( value = { "courseStudents" } )
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "course_id" )
	private Course course;
	
	@Column( name = "student_id", unique = true )
	private Long studentId;
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) {
			return true;
		}
		
		if( !( obj instanceof CourseStudent ) ) {
			return false;
		}
		CourseStudent courseStudent = (CourseStudent) obj;
		
		return this.studentId != null && this.studentId.equals( courseStudent.getStudentId() );
	}

	public Long getCourseStudentId() {
		return courseStudentId;
	}

	public void setCourseStudentId(Long courseStudentId) {
		this.courseStudentId = courseStudentId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
