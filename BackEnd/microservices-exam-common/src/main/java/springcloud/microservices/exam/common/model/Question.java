package springcloud.microservices.exam.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "question" )
public class Question implements Serializable {
	
	private static final long serialVersionUID = -9194884574746329838L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "question_id" )
	private Long questionId;
	
	@Column( name = "description" )
	private String description;
	
	@Column( name = "create_date" )
	private Date createDate;
	
	@Column( name = "modif_date" )
	private Date modifDate;
	
	@JsonIgnoreProperties( { "question" } )
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "exam_id" )
	private Exam exam;
	
	@Override
	public boolean equals(Object obj) {
		if( this == obj ) {
			return true;
		}
		if( !(obj instanceof Question) ) {
			return false;
		}
		
		Question q = ( Question ) obj;
		
		return q.getQuestionId() != null && this.questionId != null && this.questionId.equals( q.getQuestionId() );
	}
	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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
	
	@PrePersist
	public void createDate() {
		this.createDate = new Date();
	}
	
	@PreUpdate
	public void modifDate() {
		this.modifDate = new Date();
	}

}
