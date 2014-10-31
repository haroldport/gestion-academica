package gestion.academica.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo_detalle database table.
 * 
 */
@Entity
@Table(name="catalogo_detalle", schema = "gestion")
@NamedQuery(name="CatalogoDetalle.findAll", query="SELECT c FROM CatalogoDetalle c")
public class CatalogoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_catalogo_detalle")
	private Integer idCatalogoDetalle;

	private String descripcion;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_catalogo")
	private Catalogo catalogo;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="catalogoDetalle")
	private List<Cliente> clientes;
	
	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="tipoPersona")
	private List<Cliente> clientesTipoPersona;
	
	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="ciudad")
	private List<Cliente> clientesCiudad;

	//bi-directional many-to-one association to Instructor
	@OneToMany(mappedBy="catalogoDetalle")
	private List<Instructor> instructors;

	//bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy="catalogoDetalle")
	private List<Proceso> procesos;

	public CatalogoDetalle() {
	}

	public Integer getIdCatalogoDetalle() {
		return this.idCatalogoDetalle;
	}

	public void setIdCatalogoDetalle(Integer idCatalogoDetalle) {
		this.idCatalogoDetalle = idCatalogoDetalle;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	public List<Cliente> getClientesTipoPersona() {
		return clientesTipoPersona;
	}

	public void setClientesTipoPersona(List<Cliente> clientesTipoPersona) {
		this.clientesTipoPersona = clientesTipoPersona;
	}	

	public List<Cliente> getClientesCiudad() {
		return clientesCiudad;
	}

	public void setClientesCiudad(List<Cliente> clientesCiudad) {
		this.clientesCiudad = clientesCiudad;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setCatalogoDetalle(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setCatalogoDetalle(null);

		return cliente;
	}

	public List<Instructor> getInstructors() {
		return this.instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public Instructor addInstructor(Instructor instructor) {
		getInstructors().add(instructor);
		instructor.setCatalogoDetalle(this);

		return instructor;
	}

	public Instructor removeInstructor(Instructor instructor) {
		getInstructors().remove(instructor);
		instructor.setCatalogoDetalle(null);

		return instructor;
	}

	public List<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso addProceso(Proceso proceso) {
		getProcesos().add(proceso);
		proceso.setCatalogoDetalle(this);

		return proceso;
	}

	public Proceso removeProceso(Proceso proceso) {
		getProcesos().remove(proceso);
		proceso.setCatalogoDetalle(null);

		return proceso;
	}

}