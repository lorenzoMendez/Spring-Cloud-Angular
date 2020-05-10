package springcloud.microservices.answer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import springcloud.microservices.exam.common.model.Question;
import springcloud.microservices.student.common.model.Student;

@Entity
@Table( name = "answer" )
public class Answer implements Serializable {

	private static final long serialVersionUID = 2554150930139543720L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "answer_id" )
	private Long answeId;
	
	// Muchas respuestas a un estudiante
	@ManyToOne( fetch = FetchType.LAZY )
	private Student student;
	
	// Una respuesta a una pregunta
	@OneToOne( fetch = FetchType.LAZY )
	private Question question;
	
	@Column( name = "response" )
	private String response;
	
	@Column( name = "create_date" )
	private Date createDate;
	
	public Long getAnsweId() {
		return answeId;
	}

	public void setAnsweId(Long answeId) {
		this.answeId = answeId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@PrePersist
	public void createDate() {
		this.createDate = new Date();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
