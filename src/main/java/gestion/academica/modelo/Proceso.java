package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the proceso database table.
 * 
 */
@Entity
@Table(name = "proceso", schema = "gestion")
@NamedQuery(name="Proceso.findAll", query="SELECT p FROM Proceso p")
public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proceso")
	private Integer idProceso;

	private String nombre;

	//bi-directional many-to-one association to CatalogoDetalle
	@ManyToOne
	@JoinColumn(name="id_tipo_proceso")
	private CatalogoDetalle catalogoDetalle;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="id_estudiante")
	private Estudiante estudiante;

	public Proceso() {
	}

	public Integer getIdProceso() {
		return this.idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}