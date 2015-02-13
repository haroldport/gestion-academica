package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the sitio_curso database table.
 * 
 */
@Entity
@Table(name = "sitio_curso", schema = "gestion")
@NamedQuery(name = "SitioCurso.findAll", query = "SELECT s FROM SitioCurso s")
public class SitioCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sitio_curso")
	private Integer idSitioCurso;

	private String direccion;

	private String nombre;

	private String telefono;

	// bi-directional many-to-one association to Curso
	@OneToMany(mappedBy = "sitioCurso")
	private List<Curso> cursos;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	// bi-directional many-to-one association to CatalogoDetalle
	@ManyToOne
	@JoinColumn(name = "id_ciudad")
	private CatalogoDetalle ciudad;

	public SitioCurso() {
	}

	public Integer getIdSitioCurso() {
		return this.idSitioCurso;
	}

	public void setIdSitioCurso(Integer idSitioCurso) {
		this.idSitioCurso = idSitioCurso;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public CatalogoDetalle getCiudad() {
		return ciudad;
	}

	public void setCiudad(CatalogoDetalle ciudad) {
		this.ciudad = ciudad;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setSitioCurso(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setSitioCurso(null);

		return curso;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}