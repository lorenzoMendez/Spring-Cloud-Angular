package springcloud.microservices.course.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import springcloud.microservices.student.common.model.Student;

@Entity
@Table( name = "course" )
public class Course implements Serializable {
	
	private static final long serialVersionUID = -6609547332035496341L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "course_id" )
	private Long courseId;
	
	@Column( name = "description", nullable = false )
	private String description;
	
	@Column( name = "create_date", nullable = false )
	private Date createDate;
	
	@Column( name = "modif_date" )
	private Date modifDate;
	
	@Column( name = "active_id", nullable = false )
	private Integer activeId;
	
	@OneToMany( fetch = FetchType.LAZY )
	private List<Student> students = new ArrayList<Student>();
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	public Integer getActiveId() {
		return activeId;
	}

	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}

	@PrePersist
	public void createDate() {
		this.createDate = new Date();
		this.activeId = 1;
	}
	
	@PreUpdate
	public void modifDate() {
		this.modifDate = new Date();
	}

	public List<Student> getStudentList() {
		return students;
	}

	public void addStudentList( Student student ) {
		this.students.add( student );
	}
	
	public void removeStudentList( Student student ) {
		this.students.remove( student );
	}
	
}
