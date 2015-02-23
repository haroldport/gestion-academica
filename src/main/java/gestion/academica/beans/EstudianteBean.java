package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.EstudianteServicio;
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
public class EstudianteBean extends Utilitario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private EstudianteServicio estudianteServicio;
	
	private List<Estudiante> listaEstudiantes;
	private Estudiante nuevoEstudiante;
	private Estudiante eliminarEstudiante;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(EstudianteBean.class.getName());
	private Bitacora bitacora;
	private boolean editarEstudiante;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarEstudiante = new Estudiante();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarEstudiante(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoEstudiante = new Estudiante();
		nuevoEstudiante.setUsuario(new Usuario());
		listaEstudiantes = estudianteServicio.listarEstudiantes();
	}
	
	public String seleccionarEstudiante(Estudiante estudiante) {
		setEliminarEstudiante(estudiante);
		return "";
	}

	public String editarEstudiante() {
		try {
			Date fechaActualizacion = new Date();
			setEditarEstudiante(false);
			nuevoEstudiante.setNombres(nuevoEstudiante.getNombres().toUpperCase());
			nuevoEstudiante.setUsuario(null);
			estudianteServicio.editar(nuevoEstudiante);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de Estudiante: " + nuevoEstudiante.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El Estudiante fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarEstudiante(Estudiante Estudiante) {
		setEditarEstudiante(true);
		setNuevoEstudiante(Estudiante);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoEstudiante.setEstado(estadoActivo);
			nuevoEstudiante.setNombres(nuevoEstudiante.getNombres().toUpperCase());
			estudianteServicio.crear(nuevoEstudiante);
			bitacora = new Bitacora(fechaCreacion, "Creación de Estudiante: " + nuevoEstudiante.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El Estudiante fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarEstudiante.setEstado(estadoInactivo);
			estudianteServicio.editar(eliminarEstudiante);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de Estudiante: " + eliminarEstudiante.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarEstudiante = new Estudiante();
			this.ponerMensajeInfo("El Estudiante fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	
	public List<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}
	
	public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}
	
	public Estudiante getNuevoEstudiante() {
		return nuevoEstudiante;
	}
	
	public void setNuevoEstudiante(Estudiante nuevoEstudiante) {
		this.nuevoEstudiante = nuevoEstudiante;
	}
	
	public Estudiante getEliminarEstudiante() {
		return eliminarEstudiante;
	}
	
	public void setEliminarEstudiante(Estudiante eliminarEstudiante) {
		this.eliminarEstudiante = eliminarEstudiante;
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
	
	public boolean isEditarEstudiante() {
		return editarEstudiante;
	}
	
	public void setEditarEstudiante(boolean editarEstudiante) {
		this.editarEstudiante = editarEstudiante;
	}

}
