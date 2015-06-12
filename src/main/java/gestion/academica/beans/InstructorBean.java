package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Instructor;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.InstructorServicio;
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
public class InstructorBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private InstructorServicio instructorServicio;
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	
	private List<Instructor> listaInstructores;
	private List<CatalogoDetalle> tiposDocumento;
	private Instructor nuevoInstructor;
	private Instructor eliminarInstructor;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(InstructorBean.class.getName());
	private Bitacora bitacora;
	private boolean editarInstructor;
	
	@PostConstruct
	public void iniciar() {
		try {
			tiposDocumento = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_DOCUMENTO.getNemonico());
			eliminarInstructor = new Instructor();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarInstructor(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoInstructor = new Instructor();
		nuevoInstructor.setCatalogoDetalle(new CatalogoDetalle());
		listaInstructores = instructorServicio.listarInstructores();
	}
	
	public String seleccionarInstructor(Instructor instructor) {
		setEliminarInstructor(instructor);
		return "";
	}

	public String editarInstructor() {
		try {
			Date fechaActualizacion = new Date();
			setEditarInstructor(false);
			nuevoInstructor.setNombres(nuevoInstructor.getNombres().toUpperCase());
			instructorServicio.editar(nuevoInstructor);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de instructor: " + nuevoInstructor.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El instructor fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarInstructor(Instructor instructor) {
		setEditarInstructor(true);
		setNuevoInstructor(instructor);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoInstructor.setEstado(estadoActivo);
			nuevoInstructor.setNombres(nuevoInstructor.getNombres().toUpperCase());
			instructorServicio.crear(nuevoInstructor);
			bitacora = new Bitacora(fechaCreacion, "Creación de instructor: " + nuevoInstructor.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El instructor fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarInstructor.setEstado(estadoInactivo);
			instructorServicio.editar(eliminarInstructor);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de instructor: " + eliminarInstructor.getNombres(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarInstructor = new Instructor();
			this.ponerMensajeInfo("El instructor fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public List<Instructor> getListaInstructores() {
		return listaInstructores;
	}

	public void setListaInstructores(List<Instructor> listaInstructores) {
		this.listaInstructores = listaInstructores;
	}

	public List<CatalogoDetalle> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(List<CatalogoDetalle> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public Instructor getNuevoInstructor() {
		return nuevoInstructor;
	}

	public void setNuevoInstructor(Instructor nuevoInstructor) {
		this.nuevoInstructor = nuevoInstructor;
	}

	public Instructor getEliminarInstructor() {
		return eliminarInstructor;
	}

	public void setEliminarInstructor(Instructor eliminarInstructor) {
		this.eliminarInstructor = eliminarInstructor;
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

	public boolean isEditarInstructor() {
		return editarInstructor;
	}

	public void setEditarInstructor(boolean editarInstructor) {
		this.editarInstructor = editarInstructor;
	}

}
