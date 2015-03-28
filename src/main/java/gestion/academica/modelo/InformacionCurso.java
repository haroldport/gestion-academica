package gestion.academica.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the informacion_curso database table.
 * 
 */
@Entity
@Table(name="informacion_curso", schema = "gestion")
@NamedQuery(name="InformacionCurso.findAll", query="SELECT i FROM InformacionCurso i")
public class InformacionCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_informacion_curso")
	private Integer idInformacionCurso;

	private double costo;

	private String nombre;

	@Column(name="numero_horas")
	private Integer numeroHoras;

	private String objetivo;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="informacionCurso")
	private List<Curso> cursos;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Imagen
	@ManyToOne
	@JoinColumn(name="id_imagen")
	private Imagen imagen;

	//bi-directional many-to-one association to TemaCurso
	@OneToMany(mappedBy="informacionCurso", fetch = FetchType.EAGER)
	private List<TemaCurso> temaCursos;

	public InformacionCurso() {
	}

	public Integer getIdInformacionCurso() {
		return this.idInformacionCurso;
	}

	public void setIdInformacionCurso(Integer idInformacionCurso) {
		this.idInformacionCurso = idInformacionCurso;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroHoras() {
		return this.numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setInformacionCurso(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setInformacionCurso(null);

		return curso;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Imagen getImagen() {
		return this.imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public List<TemaCurso> getTemaCursos() {
		return this.temaCursos;
	}

	public void setTemaCursos(List<TemaCurso> temaCursos) {
		this.temaCursos = temaCursos;
	}

	public TemaCurso addTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().add(temaCurso);
		temaCurso.setInformacionCurso(this);

		return temaCurso;
	}

	public TemaCurso removeTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().remove(temaCurso);
		temaCurso.setInformacionCurso(null);

		return temaCurso;
	}

}