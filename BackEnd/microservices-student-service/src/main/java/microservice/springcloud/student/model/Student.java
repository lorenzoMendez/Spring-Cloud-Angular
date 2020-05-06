package microservice.springcloud.student.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "Student" )
public class Student implements Serializable {
	
	private static final long serialVersionUID = 8026397410551115631L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "student_id" )
	private Long studentId;
	
	@Column( name = "name", length = 25 )
	private String name;
	
	@Column( name = "last_name", length = 50 )
	private String lastName;
	
	@Column( name = "email", length = 35 )
	private String email;
	
	@Column( name = "status_id" )
	private Integer statusId;
	
	@Column( name = "create_date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createDate;
	
	@Column( name = "modif_date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date modifDate;
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@PrePersist
	public void createDate() {
		this.createDate = new Date();
		this.statusId = 1;
	}
	
	@PreUpdate
	public void modifDate() {
		this.modifDate = new Date();
	}
}
