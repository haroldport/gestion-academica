package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the catalogo database table.
 * 
 */
@Entity
@Table(name = "catalogo", schema = "gestion")
@NamedQuery(name="Catalogo.findAll", query="SELECT c FROM Catalogo c")
public class Catalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_catalogo")
	private Integer idCatalogo;

	private String descripcion;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to CatalogoDetalle
	@OneToMany(mappedBy="catalogo")
	private List<CatalogoDetalle> catalogoDetalles;

	public Catalogo() {
	}

	public Integer getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
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

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<CatalogoDetalle> getCatalogoDetalles() {
		return this.catalogoDetalles;
	}

	public void setCatalogoDetalles(List<CatalogoDetalle> catalogoDetalles) {
		this.catalogoDetalles = catalogoDetalles;
	}

	public CatalogoDetalle addCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		getCatalogoDetalles().add(catalogoDetalle);
		catalogoDetalle.setCatalogo(this);

		return catalogoDetalle;
	}

	public CatalogoDetalle removeCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		getCatalogoDetalles().remove(catalogoDetalle);
		catalogoDetalle.setCatalogo(null);

		return catalogoDetalle;
	}

}