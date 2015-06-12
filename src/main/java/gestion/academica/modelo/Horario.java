package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@Table(name = "horario", schema = "gestion")
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_horario")
	private Integer idHorario;

	private String dias;

	private String horas;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="horario")
	private List<Curso> cursos;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	public Horario() {
	}

	public Integer getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public String getDias() {
		return this.dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHoras() {
		return this.horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setHorario(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setHorario(null);

		return curso;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}