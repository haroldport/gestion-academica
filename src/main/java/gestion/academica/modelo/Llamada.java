package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the llamada database table.
 * 
 */
@Entity
@Table(name = "llamada", schema = "gestion")
@NamedQuery(name="Llamada.findAll", query="SELECT l FROM Llamada l")
public class Llamada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_llamada")
	private Integer idLlamada;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_llamada_proxima")
	private Date fechaLlamadaProxima;

	private String observaciones;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	public Llamada() {
	}

	public Integer getIdLlamada() {
		return this.idLlamada;
	}

	public void setIdLlamada(Integer idLlamada) {
		this.idLlamada = idLlamada;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaLlamadaProxima() {
		return this.fechaLlamadaProxima;
	}

	public void setFechaLlamadaProxima(Date fechaLlamadaProxima) {
		this.fechaLlamadaProxima = fechaLlamadaProxima;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}