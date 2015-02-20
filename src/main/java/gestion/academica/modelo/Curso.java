package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@Table(name = "curso", schema = "gestion")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_curso")
	private Integer idCurso;

	private Integer cupo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Horario
	@ManyToOne
	@JoinColumn(name="id_horario")
	private Horario horario;

	//bi-directional many-to-one association to InformacionCurso
	@ManyToOne
	@JoinColumn(name="id_informacion_curso")
	private InformacionCurso informacionCurso;

	//bi-directional many-to-one association to Instructor
	@ManyToOne
	@JoinColumn(name="id_instructor")
	private Instructor instructor;

	//bi-directional many-to-one association to SitioCurso
	@ManyToOne
	@JoinColumn(name="id_sitio_curso")
	private SitioCurso sitioCurso;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="curso")
	private List<Matricula> matriculas;

	//bi-directional many-to-one association to Preinscripcion
	@OneToMany(mappedBy="curso")
	private List<Preinscripcion> preinscripcions;
	
	@OneToMany(mappedBy="curso")
	private List<Llamada> llamadas;

	public Curso() {
	}

	public Integer getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Integer getCupo() {
		return this.cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public InformacionCurso getInformacionCurso() {
		return this.informacionCurso;
	}

	public void setInformacionCurso(InformacionCurso informacionCurso) {
		this.informacionCurso = informacionCurso;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public SitioCurso getSitioCurso() {
		return this.sitioCurso;
	}

	public void setSitioCurso(SitioCurso sitioCurso) {
		this.sitioCurso = sitioCurso;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCurso(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCurso(null);

		return matricula;
	}

	public List<Preinscripcion> getPreinscripcions() {
		return this.preinscripcions;
	}

	public void setPreinscripcions(List<Preinscripcion> preinscripcions) {
		this.preinscripcions = preinscripcions;
	}	

	public List<Llamada> getLlamadas() {
		return llamadas;
	}

	public void setLlamadas(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public Preinscripcion addPreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().add(preinscripcion);
		preinscripcion.setCurso(this);

		return preinscripcion;
	}

	public Preinscripcion removePreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().remove(preinscripcion);
		preinscripcion.setCurso(null);

		return preinscripcion;
	}

}