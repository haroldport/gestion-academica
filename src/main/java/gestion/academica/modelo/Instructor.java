package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the instructor database table.
 * 
 */
@Entity
@Table(name = "instructor", schema = "gestion")
@NamedQuery(name="Instructor.findAll", query="SELECT i FROM Instructor i")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_instructor")
	private Integer idInstructor;

	private String email;

	private String nombres;

	@Column(name="numero_documento")
	private String numeroDocumento;

	private String telefono;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="instructor")
	private List<Curso> cursos;

	//bi-directional many-to-one association to CatalogoDetalle
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private CatalogoDetalle catalogoDetalle;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	public Instructor() {
	}

	public Integer getIdInstructor() {
		return this.idInstructor;
	}

	public void setIdInstructor(Integer idInstructor) {
		this.idInstructor = idInstructor;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setInstructor(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setInstructor(null);

		return curso;
	}

	public CatalogoDetalle getCatalogoDetalle() {
		return this.catalogoDetalle;
	}

	public void setCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		this.catalogoDetalle = catalogoDetalle;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}