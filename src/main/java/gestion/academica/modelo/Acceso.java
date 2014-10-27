package gestion.academica.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the acceso database table.
 * 
 */
@Entity
@Table(name = "acceso", schema = "gestion")
@NamedQuery(name="Acceso.findAll", query="SELECT a FROM Acceso a")
public class Acceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acceso")
	private Integer idAcceso;

	private String descripcion;

	private String etiqueta;

	private Integer orden;

	private String tipo;

	private String url;

	//bi-directional many-to-one association to Acceso
	@ManyToOne
	@JoinColumn(name="id_acceso_padre")
	private Acceso acceso;

	//bi-directional many-to-one association to Acceso
	@OneToMany(mappedBy="acceso", fetch = FetchType.EAGER)
	private List<Acceso> accesos;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to AccesoRol
	@OneToMany(mappedBy="acceso")
	private List<AccesoRol> accesoRols;

	public Acceso() {
	}

	public Integer getIdAcceso() {
		return this.idAcceso;
	}

	public void setIdAcceso(Integer idAcceso) {
		this.idAcceso = idAcceso;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEtiqueta() {
		return this.etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Acceso getAcceso() {
		return this.acceso;
	}

	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	public List<Acceso> getAccesos() {
		return this.accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public Acceso addAcceso(Acceso acceso) {
		getAccesos().add(acceso);
		acceso.setAcceso(this);

		return acceso;
	}

	public Acceso removeAcceso(Acceso acceso) {
		getAccesos().remove(acceso);
		acceso.setAcceso(null);

		return acceso;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<AccesoRol> getAccesoRols() {
		return this.accesoRols;
	}

	public void setAccesoRols(List<AccesoRol> accesoRols) {
		this.accesoRols = accesoRols;
	}

	public AccesoRol addAccesoRol(AccesoRol accesoRol) {
		getAccesoRols().add(accesoRol);
		accesoRol.setAcceso(this);

		return accesoRol;
	}

	public AccesoRol removeAccesoRol(AccesoRol accesoRol) {
		getAccesoRols().remove(accesoRol);
		accesoRol.setAcceso(null);

		return accesoRol;
	}

}