package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Horario;
import gestion.academica.modelo.InformacionCurso;
import gestion.academica.modelo.Instructor;
import gestion.academica.modelo.SitioCurso;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CursoServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.HorarioServicio;
import gestion.academica.servicio.InformacionCursoServicio;
import gestion.academica.servicio.InstructorServicio;
import gestion.academica.servicio.SitioCursoServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CursoBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private CursoServicio cursoServicio;
	@EJB
	private InstructorServicio instructorServicio;
	@EJB
	private HorarioServicio horarioServicio;
	@EJB
	private InformacionCursoServicio informacionCursoServicio;
	@EJB
	private SitioCursoServicio sitioCursoServicio;
	
	private Curso nuevoCurso;
	private Curso eliminarCurso;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(CursoBean.class.getName());
	private Bitacora bitacora;
	private boolean editarCurso;
	private List<Curso> listaCursos;
	private List<InformacionCurso> informacionCursos;
	private List<Horario> horarios;
	private List<Instructor> instructores;
	private List<SitioCurso> sitios;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarCurso = new Curso();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			informacionCursos = informacionCursoServicio.listarInformacionDeCursos();
			horarios = horarioServicio.listarHorarios();
			instructores = instructorServicio.listarInstructores();
			sitios = sitioCursoServicio.listarSitios();
			initValores();
			this.setEditarCurso(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoCurso = new Curso();
		nuevoCurso.setInformacionCurso(new InformacionCurso());
		nuevoCurso.setHorario(new Horario());
		nuevoCurso.setInstructor(new Instructor());
		nuevoCurso.setSitioCurso(new SitioCurso());
		listaCursos = cursoServicio.listarCursos();
	}
	
	public String seleccionarCurso(Curso curso) {
		setEliminarCurso(curso);
		return "";
	}

	public String editarCurso() {
		try {
			Date fechaActualizacion = new Date();
			setEditarCurso(false);
			cursoServicio.editar(nuevoCurso);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de curso: " + nuevoCurso.getInformacionCurso().getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El curso fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarCurso(Curso curso) {
		setEditarCurso(true);
		setNuevoCurso(curso);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoCurso.setEstado(estadoActivo);
			cursoServicio.crear(nuevoCurso);
			bitacora = new Bitacora(fechaCreacion, "Creación de curso: " + nuevoCurso.getInformacionCurso().getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El curso fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarCurso.setEstado(estadoInactivo);
			cursoServicio.editar(eliminarCurso);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de sitio: " + nuevoCurso.getInformacionCurso().getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarCurso = new Curso();
			this.ponerMensajeInfo("El curso fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public Curso getNuevoCurso() {
		return nuevoCurso;
	}
	public void setNuevoCurso(Curso nuevoCurso) {
		this.nuevoCurso = nuevoCurso;
	}
	public Curso getEliminarCurso() {
		return eliminarCurso;
	}
	public void setEliminarCurso(Curso eliminarCurso) {
		this.eliminarCurso = eliminarCurso;
	}
	public Estado getEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(Estado estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	public Estado getEstadoInactivo() {
		return estadoInactivo;
	}
	public void setEstadoInactivo(Estado estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
	}
	public Bitacora getBitacora() {
		return bitacora;
	}
	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}
	public boolean isEditarCurso() {
		return editarCurso;
	}
	public void setEditarCurso(boolean editarCurso) {
		this.editarCurso = editarCurso;
	}
	public List<Curso> getListaCursos() {
		return listaCursos;
	}
	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<InformacionCurso> getInformacionCursos() {
		return informacionCursos;
	}

	public void setInformacionCursos(List<InformacionCurso> informacionCursos) {
		this.informacionCursos = informacionCursos;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<Instructor> getInstructores() {
		return instructores;
	}

	public void setInstructores(List<Instructor> instructores) {
		this.instructores = instructores;
	}

	public List<SitioCurso> getSitios() {
		return sitios;
	}

	public void setSitios(List<SitioCurso> sitios) {
		this.sitios = sitios;
	}

}
