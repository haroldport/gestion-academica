package gestion.academica.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the catalogo_detalle database table.
 * 
 */
@Entity
@Table(name = "catalogo_detalle", schema = "gestion")
@NamedQuery(name = "CatalogoDetalle.findAll", query = "SELECT c FROM CatalogoDetalle c")
public class CatalogoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_catalogo_detalle")
	private Integer idCatalogoDetalle;

	private String descripcion;

	private String nemonico;

	private String nombre;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "id_catalogo")
	private Catalogo catalogo;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	// bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy = "catalogoDetalle")
	private List<Cliente> clientes;

	// bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy = "tipoPersona")
	private List<Cliente> clientesTipoPersona;

	// bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy = "ciudad")
	private List<Cliente> clientesCiudad;

	// bi-directional many-to-one association to Instructor
	@OneToMany(mappedBy = "catalogoDetalle")
	private List<Instructor> instructors;

	// bi-directional many-to-one association to SitioCurso
	@OneToMany(mappedBy = "ciudad")
	private List<SitioCurso> sitioCursoCiudad;
	
	@JoinColumn(name = "id_catalogo_detalle_padre", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idCatalogoDetallePadre;
	
	@OneToMany(mappedBy = "idCatalogoDetallePadre", fetch = FetchType.EAGER)
    private List<CatalogoDetalle> catalogoDetalleList;

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
	
	public List<SitioCurso> getSitioCursoCiudad() {
		return sitioCursoCiudad;
	}

	public void setSitioCursoCiudad(List<SitioCurso> sitioCursoCiudad) {
		this.sitioCursoCiudad = sitioCursoCiudad;
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
	
	@XmlTransient
    public List<CatalogoDetalle> getCatalogoDetalleList() {
        return catalogoDetalleList;
    }

    public void setCatalogoDetalleList(List<CatalogoDetalle> catalogoDetalleList) {
        this.catalogoDetalleList = catalogoDetalleList;
    }
	public CatalogoDetalle getIdCatalogoDetallePadre() {
        return idCatalogoDetallePadre;
    }

    public void setIdCatalogoDetallePadre(CatalogoDetalle idCatalogoDetallePadre) {
        this.idCatalogoDetallePadre = idCatalogoDetallePadre;
    }
}