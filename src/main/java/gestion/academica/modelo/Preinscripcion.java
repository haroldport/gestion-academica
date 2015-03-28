package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the preinscripcion database table.
 * 
 */
@Entity
@Table(name = "preinscripcion", schema = "gestion")
@NamedQuery(name="Preinscripcion.findAll", query="SELECT p FROM Preinscripcion p")
public class Preinscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_preinscripcion")
	private Integer idPreinscripcion;

	@Column(name="numero_participantes")
	private Integer numeroParticipantes;

	private String observacion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	private boolean matriculado;

	public Preinscripcion() {
	}

	public Preinscripcion(String observacion, Cliente cliente, Curso curso,
			Estado estado) {
		this.observacion = observacion;
		this.cliente = cliente;
		this.curso = curso;
		this.estado = estado;
	}



	public Integer getIdPreinscripcion() {
		return this.idPreinscripcion;
	}

	public void setIdPreinscripcion(Integer idPreinscripcion) {
		this.idPreinscripcion = idPreinscripcion;
	}

	public Integer getNumeroParticipantes() {
		return this.numeroParticipantes;
	}

	public void setNumeroParticipantes(Integer numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}

}