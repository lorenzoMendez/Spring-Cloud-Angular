package springcloud.microservices.student.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "Student" )
public class Student implements Serializable {
	
	private static final long serialVersionUID = 8026397410551115631L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "student_id" )
	private Long id;

	@NotEmpty( message = "El nombre es obligatorio")
	@Size( min = 3, max = 25, message = "Tamaño entre 3 y 25 caracteres" )
	@Column( name = "name", length = 25 )
	private String name;
	
	@NotEmpty( message = "El apellido es obligatorio" )
	@Size( min = 3, max = 50, message = "Tamaño entre 3 y 50 caracteres" )
	@Column( name = "last_name", length = 50 )
	private String lastName;
	
	@NotEmpty( message = "El correo es obligatorio" )
	@Email( message = "El correo no tiene el formato adecuado" )//(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" )
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
	
	// Solo lo utilizaremos para persistir la imagen
	@JsonIgnore
	@Lob
	@Column( name = "photo" )
	private byte[] photo;
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public boolean equals( Object obj ) {
		
		if( this == obj ) {
			return true;
		}
		
		if( !( obj instanceof Student ) ) {
			return false;
		}
		
		Student student = ( Student )obj;
		
		return this.id != null && this.id.equals( student.getId() );
	}
	
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Integer getPhotoHashCode() {
		return ( this.photo != null ? this.photo.hashCode() : null );
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
