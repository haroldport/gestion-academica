package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Matricula;
import gestion.academica.modelo.Rol;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CursoServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.EstudianteServicio;
import gestion.academica.servicio.MatriculaServicio;
import gestion.academica.servicio.RolServicio;
import gestion.academica.utilitario.Crypt;
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
public class MatriculaBean extends Utilitario implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private MatriculaServicio MatriculaServicio;
	@EJB
	private MatriculaServicio matriculaServicio;
	@EJB
	private RolServicio rolServicio;
	@EJB
	private CursoServicio cursoServicio;
	@EJB
	private EstudianteServicio estudianteServicio;
	
	private Matricula nuevaMatricula;
	private Matricula eliminarMatricula;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(MatriculaBean.class.getName());
	private Bitacora bitacora;
	private boolean editarMatricula;
	private Rol rolMatriculado;
	private List<Matricula> listaMatriculas;
	private List<Curso> listaCursos;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarMatricula = new Matricula();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			rolMatriculado = rolServicio.obtenerPorNemonico("RMAT");
			listaCursos = cursoServicio.listarCursos();
			initValores();
			this.setEditarMatricula(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevaMatricula = new Matricula();
		nuevaMatricula.setCurso(new Curso());
		nuevaMatricula.setEstudiante(new Estudiante());
		nuevaMatricula.getEstudiante().setUsuario(new Usuario());
		listaMatriculas = matriculaServicio.listarMatriculas();
	}
	
	public void verificarEstudianteExiste(){
		Estudiante estudiante = estudianteServicio.buscarPorNumeroDocumento(nuevaMatricula.getEstudiante().getNumeroDocumento());
		if(estudiante != null){
			nuevaMatricula.setEstudiante(estudiante);
		}
	}
	
	public String seleccionarMatricula(Matricula Matricula) {
		setEliminarMatricula(Matricula);
		return "";
	}

	public String editarMatricula() {
		try {
			Date fechaActualizacion = new Date();
			setEditarMatricula(false);
			nuevaMatricula.getEstudiante().setNombres(nuevaMatricula.getEstudiante().getNombres().toUpperCase());			
			matriculaServicio.editarEstudianteYMatricula(nuevaMatricula.getEstudiante(), nuevaMatricula);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de Matricula: " + nuevaMatricula.getIdMatricula(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("La Matricula fue actualizada correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarMatricula(Matricula matricula) {
		setEditarMatricula(true);
		setNuevaMatricula(matricula);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			Curso curso = cursoServicio.buscarPorId(nuevaMatricula.getCurso().getIdCurso());
			nuevaMatricula.setCurso(curso);
			Matricula matricula = matriculaServicio.buscarPorEstudianteYCurso(nuevaMatricula.getEstudiante(), nuevaMatricula.getCurso());
			if(matricula == null){
				nuevaMatricula.setEstado(estadoActivo);
				nuevaMatricula.setFechaMatricula(fechaCreacion);
				nuevaMatricula.getEstudiante().setNombres(nuevaMatricula.getEstudiante().getNombres().toUpperCase());
				if(nuevaMatricula.getEstudiante().getUsuario().getUsername() == null){
					nuevaMatricula.getEstudiante().getUsuario().setUsername(nuevaMatricula.getEstudiante().getNumeroDocumento());
					nuevaMatricula.getEstudiante().getUsuario().setClave(Crypt.encryptMD5(nuevaMatricula.getEstudiante().getNumeroDocumento()));
					nuevaMatricula.getEstudiante().getUsuario().setEstado(estadoActivo);
				}
				matriculaServicio.crearEstudianteYMatricula(nuevaMatricula.getEstudiante(), nuevaMatricula, rolMatriculado);
				bitacora = new Bitacora(fechaCreacion, "Creación de Matricula: " + nuevaMatricula.getIdMatricula(), this.getUsuario());
	            bitacoraServicio.crear(bitacora);
				this.ponerMensajeInfo("La Matricula fue creada correctamente", "");
				initValores();
			}else{
				ponerMensajeError("Error - El(la) estudiante: " + nuevaMatricula.getEstudiante().getNombres() + 
						", ya está matriculad@ en el curso: " + nuevaMatricula.getCurso().getInformacionCurso().getNombre(), "");
			}			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarMatricula.setEstado(estadoInactivo);
			matriculaServicio.editar(eliminarMatricula);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de Matricula: " + eliminarMatricula.getIdMatricula(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarMatricula = new Matricula();
			this.ponerMensajeInfo("La Matricula fue eliminada correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public Matricula getNuevaMatricula() {
		return nuevaMatricula;
	}

	public void setNuevaMatricula(Matricula nuevaMatricula) {
		this.nuevaMatricula = nuevaMatricula;
	}

	public Matricula getEliminarMatricula() {
		return eliminarMatricula;
	}

	public void setEliminarMatricula(Matricula eliminarMatricula) {
		this.eliminarMatricula = eliminarMatricula;
	}

	public boolean isEditarMatricula() {
		return editarMatricula;
	}

	public void setEditarMatricula(boolean editarMatricula) {
		this.editarMatricula = editarMatricula;
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

	public Rol getRolMatriculado() {
		return rolMatriculado;
	}

	public void setRolMatriculado(Rol rolMatriculado) {
		this.rolMatriculado = rolMatriculado;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

}
