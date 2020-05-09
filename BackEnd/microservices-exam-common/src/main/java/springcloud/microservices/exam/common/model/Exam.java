package springcloud.microservices.exam.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "exam" )
public class Exam implements Serializable {
	
	private static final long serialVersionUID = -1158868828115652538L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "exam_id" )
	private Long examId;
	
	@NotEmpty
	@Column( name = "description" )
	private String description;
	
	@Column( name = "active_id" )
	private Integer activeId;
	
	@Column( name = "create_date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createDate;
	
	@Column( name = "modif_date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date modifDate;
	
	@JsonIgnoreProperties( value = { "exam" }, allowSetters=true )
	@OneToMany( mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
	List<Question> questions = new ArrayList<Question>();
	
	@NotNull
	@ManyToOne( fetch = FetchType.LAZY )
	private Subject subject;
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions ) {
		this.questions.clear();
		// questions.forEach( q -> this.addQuestion( q ) );
		questions.forEach( this::addQuestion );
	}
	
	public void addQuestion( Question question ) {
		this.questions.add( question );
		question.setExam( this );
	}
	
	public void removeQuestion( Question question ) {
		this.questions.remove( question );
		question.setExam( null );
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActiveId() {
		return activeId;
	}

	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
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
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public boolean equals( Object obj ) {
		
		if( this == obj ) {
			return true;
		}
		
		if( !( obj instanceof Exam ) ) {
			return false;
		}
		
		Exam exam = ( Exam ) obj;
		
		return this.examId != null && exam.getExamId() != null && this.examId.equals( exam.getExamId() );
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
}
