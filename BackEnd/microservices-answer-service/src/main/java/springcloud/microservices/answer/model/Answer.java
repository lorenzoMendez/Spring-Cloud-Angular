package springcloud.microservices.answer.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import springcloud.microservices.exam.common.model.Question;
import springcloud.microservices.student.common.model.Student;

@Document( collection = "answer" )
public class Answer implements Serializable {

	private static final long serialVersionUID = 2554150930139543720L;
	
	@Id
	private String answeId;

	// @Transient
	private Student student;
	
	private Long studentId;
	
	// Especifica que es parte de la clase pero no del mapeo
	// @Transient
	private Question question;
	
	private String response;
	
	private Long questionId;
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	public String getAnsweId() {
		return answeId;
	}

	public void setAnsweId( String answeId ) {
		this.answeId = answeId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
}
