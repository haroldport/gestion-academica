package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "cliente", schema = "gestion")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Integer idCliente;

	private String celular;

	private String direccion;

	private String email;

	private String nombres;

	@Column(name="numero_documento")
	private String numeroDocumento;

	private String telefono;

	//bi-directional many-to-one association to CatalogoDetalle
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private CatalogoDetalle catalogoDetalle;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Llamada
	@OneToMany(mappedBy="cliente")
	private List<Llamada> llamadas;

	//bi-directional many-to-one association to Preinscripcion
	@OneToMany(mappedBy="cliente")
	private List<Preinscripcion> preinscripcions;

	public Cliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public List<Llamada> getLlamadas() {
		return this.llamadas;
	}

	public void setLlamadas(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public Llamada addLlamada(Llamada llamada) {
		getLlamadas().add(llamada);
		llamada.setCliente(this);

		return llamada;
	}

	public Llamada removeLlamada(Llamada llamada) {
		getLlamadas().remove(llamada);
		llamada.setCliente(null);

		return llamada;
	}

	public List<Preinscripcion> getPreinscripcions() {
		return this.preinscripcions;
	}

	public void setPreinscripcions(List<Preinscripcion> preinscripcions) {
		this.preinscripcions = preinscripcions;
	}

	public Preinscripcion addPreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().add(preinscripcion);
		preinscripcion.setCliente(this);

		return preinscripcion;
	}

	public Preinscripcion removePreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().remove(preinscripcion);
		preinscripcion.setCliente(null);

		return preinscripcion;
	}

}