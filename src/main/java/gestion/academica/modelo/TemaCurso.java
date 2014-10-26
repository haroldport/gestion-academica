package gestion.academica.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tema_curso database table.
 * 
 */
@Entity
@Table(name="tema_curso", schema = "gestion")
@NamedQuery(name="TemaCurso.findAll", query="SELECT t FROM TemaCurso t")
public class TemaCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tema_curso")
	private Integer idTemaCurso;

	private String descripcion;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to InformacionCurso
	@ManyToOne
	@JoinColumn(name="id_informacion_curso")
	private InformacionCurso informacionCurso;

	//bi-directional many-to-one association to TemaCurso
	@ManyToOne
	@JoinColumn(name="id_tema_curso_padre")
	private TemaCurso temaCurso;

	//bi-directional many-to-one association to TemaCurso
	@OneToMany(mappedBy="temaCurso")
	private List<TemaCurso> temaCursos;

	public TemaCurso() {
	}

	public Integer getIdTemaCurso() {
		return this.idTemaCurso;
	}

	public void setIdTemaCurso(Integer idTemaCurso) {
		this.idTemaCurso = idTemaCurso;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public InformacionCurso getInformacionCurso() {
		return this.informacionCurso;
	}

	public void setInformacionCurso(InformacionCurso informacionCurso) {
		this.informacionCurso = informacionCurso;
	}

	public TemaCurso getTemaCurso() {
		return this.temaCurso;
	}

	public void setTemaCurso(TemaCurso temaCurso) {
		this.temaCurso = temaCurso;
	}

	public List<TemaCurso> getTemaCursos() {
		return this.temaCursos;
	}

	public void setTemaCursos(List<TemaCurso> temaCursos) {
		this.temaCursos = temaCursos;
	}

	public TemaCurso addTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().add(temaCurso);
		temaCurso.setTemaCurso(this);

		return temaCurso;
	}

	public TemaCurso removeTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().remove(temaCurso);
		temaCurso.setTemaCurso(null);

		return temaCurso;
	}

}