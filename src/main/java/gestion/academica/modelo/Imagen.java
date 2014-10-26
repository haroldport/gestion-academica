package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the imagen database table.
 * 
 */
@Entity
@Table(name = "imagen", schema = "gestion")
@NamedQuery(name="Imagen.findAll", query="SELECT i FROM Imagen i")
public class Imagen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_imagen")
	private Integer idImagen;

	private byte[] archivo;

	private String extension;

	private String nombre;

	@Column(name="tipo_archivo")
	private String tipoArchivo;

	//bi-directional many-to-one association to InformacionCurso
	@OneToMany(mappedBy="imagen")
	private List<InformacionCurso> informacionCursos;

	public Imagen() {
	}

	public Integer getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoArchivo() {
		return this.tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public List<InformacionCurso> getInformacionCursos() {
		return this.informacionCursos;
	}

	public void setInformacionCursos(List<InformacionCurso> informacionCursos) {
		this.informacionCursos = informacionCursos;
	}

	public InformacionCurso addInformacionCurso(InformacionCurso informacionCurso) {
		getInformacionCursos().add(informacionCurso);
		informacionCurso.setImagen(this);

		return informacionCurso;
	}

	public InformacionCurso removeInformacionCurso(InformacionCurso informacionCurso) {
		getInformacionCursos().remove(informacionCurso);
		informacionCurso.setImagen(null);

		return informacionCurso;
	}

}