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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "subject" )
public class Subject implements Serializable {
	
	private static final long serialVersionUID = 439118489137056051L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "subject_id" )
	private Long subjectId;
	
	@NotNull( message = "El nombre de la materia es obligatoria" )
	@Column( name = "description" )
	private String description;
	
	@Column( name = "active_id" )
	private Integer activeId;
	
	@Column( name = "create_date" )
	private Date createDate;
	
	@Column( name = "modif_date" )
	private Date modifDate;
	
	@JsonIgnoreProperties( value = { "sons" } )
	@ManyToOne( fetch = FetchType.LAZY )
	private Subject father;
	
	@JsonIgnoreProperties( value = { "father" }, allowSetters = true )
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "father", cascade = CascadeType.ALL )
	private List<Subject> sons = new ArrayList<Subject>();
	
	public Subject getFather() {
		return father;
	}

	public void setFather(Subject father) {
		this.father = father;
	}

	public List<Subject> getSons() {
		return sons;
	}

	public void setSons(List<Subject> sons) {
		this.sons = sons;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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
	
	@PrePersist
	public void createDate() {
		this.activeId = 1;
		this.createDate = new Date();
	}
	
	@PreUpdate
	public void modifDate() {
		this.modifDate = new Date();
	}
}
