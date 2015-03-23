package gestion.academica.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name = "estado", schema = "gestion")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado")
	private Integer idEstado;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to Acceso
	@OneToMany(mappedBy="estado")
	private List<Acceso> accesos;

	//bi-directional many-to-one association to AccesoRol
	@OneToMany(mappedBy="estado")
	private List<AccesoRol> accesoRols;

	//bi-directional many-to-one association to Catalogo
	@OneToMany(mappedBy="estado")
	private List<Catalogo> catalogos;

	//bi-directional many-to-one association to CatalogoDetalle
	@OneToMany(mappedBy="estado")
	private List<CatalogoDetalle> catalogoDetalles;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="estado")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="estado")
	private List<Curso> cursos;

	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="estado")
	private List<Estudiante> estudiantes;

	//bi-directional many-to-one association to Horario
	@OneToMany(mappedBy="estado")
	private List<Horario> horarios;

	//bi-directional many-to-one association to InformacionCurso
	@OneToMany(mappedBy="estado")
	private List<InformacionCurso> informacionCursos;

	//bi-directional many-to-one association to Instructor
	@OneToMany(mappedBy="estado")
	private List<Instructor> instructors;

	//bi-directional many-to-one association to Llamada
	@OneToMany(mappedBy="estado")
	private List<Llamada> llamadas;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="estado")
	private List<Matricula> matriculas;

	//bi-directional many-to-one association to Preinscripcion
	@OneToMany(mappedBy="estado")
	private List<Preinscripcion> preinscripcions;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="estado")
	private List<Rol> rols;

	//bi-directional many-to-one association to SitioCurso
	@OneToMany(mappedBy="estado")
	private List<SitioCurso> sitioCursos;

	//bi-directional many-to-one association to TemaCurso
	@OneToMany(mappedBy="estado")
	private List<TemaCurso> temaCursos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="estado")
	private List<Usuario> usuarios;

	public Estado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
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

	public List<Acceso> getAccesos() {
		return this.accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public Acceso addAcceso(Acceso acceso) {
		getAccesos().add(acceso);
		acceso.setEstado(this);

		return acceso;
	}

	public Acceso removeAcceso(Acceso acceso) {
		getAccesos().remove(acceso);
		acceso.setEstado(null);

		return acceso;
	}

	public List<AccesoRol> getAccesoRols() {
		return this.accesoRols;
	}

	public void setAccesoRols(List<AccesoRol> accesoRols) {
		this.accesoRols = accesoRols;
	}

	public AccesoRol addAccesoRol(AccesoRol accesoRol) {
		getAccesoRols().add(accesoRol);
		accesoRol.setEstado(this);

		return accesoRol;
	}

	public AccesoRol removeAccesoRol(AccesoRol accesoRol) {
		getAccesoRols().remove(accesoRol);
		accesoRol.setEstado(null);

		return accesoRol;
	}

	public List<Catalogo> getCatalogos() {
		return this.catalogos;
	}

	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	public Catalogo addCatalogo(Catalogo catalogo) {
		getCatalogos().add(catalogo);
		catalogo.setEstado(this);

		return catalogo;
	}

	public Catalogo removeCatalogo(Catalogo catalogo) {
		getCatalogos().remove(catalogo);
		catalogo.setEstado(null);

		return catalogo;
	}

	public List<CatalogoDetalle> getCatalogoDetalles() {
		return this.catalogoDetalles;
	}

	public void setCatalogoDetalles(List<CatalogoDetalle> catalogoDetalles) {
		this.catalogoDetalles = catalogoDetalles;
	}

	public CatalogoDetalle addCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		getCatalogoDetalles().add(catalogoDetalle);
		catalogoDetalle.setEstado(this);

		return catalogoDetalle;
	}

	public CatalogoDetalle removeCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		getCatalogoDetalles().remove(catalogoDetalle);
		catalogoDetalle.setEstado(null);

		return catalogoDetalle;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setEstado(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setEstado(null);

		return cliente;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setEstado(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setEstado(null);

		return curso;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setEstado(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setEstado(null);

		return estudiante;
	}

	public List<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Horario addHorario(Horario horario) {
		getHorarios().add(horario);
		horario.setEstado(this);

		return horario;
	}

	public Horario removeHorario(Horario horario) {
		getHorarios().remove(horario);
		horario.setEstado(null);

		return horario;
	}

	public List<InformacionCurso> getInformacionCursos() {
		return this.informacionCursos;
	}

	public void setInformacionCursos(List<InformacionCurso> informacionCursos) {
		this.informacionCursos = informacionCursos;
	}

	public InformacionCurso addInformacionCurso(InformacionCurso informacionCurso) {
		getInformacionCursos().add(informacionCurso);
		informacionCurso.setEstado(this);

		return informacionCurso;
	}

	public InformacionCurso removeInformacionCurso(InformacionCurso informacionCurso) {
		getInformacionCursos().remove(informacionCurso);
		informacionCurso.setEstado(null);

		return informacionCurso;
	}

	public List<Instructor> getInstructors() {
		return this.instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public Instructor addInstructor(Instructor instructor) {
		getInstructors().add(instructor);
		instructor.setEstado(this);

		return instructor;
	}

	public Instructor removeInstructor(Instructor instructor) {
		getInstructors().remove(instructor);
		instructor.setEstado(null);

		return instructor;
	}

	public List<Llamada> getLlamadas() {
		return this.llamadas;
	}

	public void setLlamadas(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public Llamada addLlamada(Llamada llamada) {
		getLlamadas().add(llamada);
		llamada.setEstado(this);

		return llamada;
	}

	public Llamada removeLlamada(Llamada llamada) {
		getLlamadas().remove(llamada);
		llamada.setEstado(null);

		return llamada;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setEstado(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setEstado(null);

		return matricula;
	}

	public List<Preinscripcion> getPreinscripcions() {
		return this.preinscripcions;
	}

	public void setPreinscripcions(List<Preinscripcion> preinscripcions) {
		this.preinscripcions = preinscripcions;
	}

	public Preinscripcion addPreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().add(preinscripcion);
		preinscripcion.setEstado(this);

		return preinscripcion;
	}

	public Preinscripcion removePreinscripcion(Preinscripcion preinscripcion) {
		getPreinscripcions().remove(preinscripcion);
		preinscripcion.setEstado(null);

		return preinscripcion;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setEstado(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setEstado(null);

		return rol;
	}

	public List<SitioCurso> getSitioCursos() {
		return this.sitioCursos;
	}

	public void setSitioCursos(List<SitioCurso> sitioCursos) {
		this.sitioCursos = sitioCursos;
	}

	public SitioCurso addSitioCurso(SitioCurso sitioCurso) {
		getSitioCursos().add(sitioCurso);
		sitioCurso.setEstado(this);

		return sitioCurso;
	}

	public SitioCurso removeSitioCurso(SitioCurso sitioCurso) {
		getSitioCursos().remove(sitioCurso);
		sitioCurso.setEstado(null);

		return sitioCurso;
	}

	public List<TemaCurso> getTemaCursos() {
		return this.temaCursos;
	}

	public void setTemaCursos(List<TemaCurso> temaCursos) {
		this.temaCursos = temaCursos;
	}

	public TemaCurso addTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().add(temaCurso);
		temaCurso.setEstado(this);

		return temaCurso;
	}

	public TemaCurso removeTemaCurso(TemaCurso temaCurso) {
		getTemaCursos().remove(temaCurso);
		temaCurso.setEstado(null);

		return temaCurso;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEstado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEstado(null);

		return usuario;
	}

}