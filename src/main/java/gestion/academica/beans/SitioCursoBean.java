package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.SitioCurso;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
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
public class SitioCursoBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private SitioCursoServicio sitioCursoServicio;
	
	private SitioCurso nuevoSitioCurso;
	private SitioCurso eliminarSitioCurso;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(SitioCursoBean.class.getName());
	private Bitacora bitacora;
	private boolean editarSitioCurso;
	private List<SitioCurso> listadoSitios;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarSitioCurso = new SitioCurso();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarSitioCurso(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoSitioCurso = new SitioCurso();
		nuevoSitioCurso.setCiudad(new CatalogoDetalle());
		listadoSitios = sitioCursoServicio.listarSitios();
	}
	
	public String seleccionarSitioCurso(SitioCurso sitioCurso) {
		setEliminarSitioCurso(sitioCurso);
		return "";
	}

	public String editarSitioCurso() {
		try {
			Date fechaActualizacion = new Date();
			setEditarSitioCurso(false);
			nuevoSitioCurso.setNombre(nuevoSitioCurso.getNombre().toUpperCase());
			nuevoSitioCurso.setDireccion(nuevoSitioCurso.getDireccion().toUpperCase());
			sitioCursoServicio.editar(nuevoSitioCurso);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de sitio: " + nuevoSitioCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El sitio fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarSitioCurso(SitioCurso sitioCurso) {
		setEditarSitioCurso(true);
		setNuevoSitioCurso(sitioCurso);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoSitioCurso.setEstado(estadoActivo);
			nuevoSitioCurso.setNombre(nuevoSitioCurso.getNombre().toUpperCase());
			nuevoSitioCurso.setDireccion(nuevoSitioCurso.getDireccion().toUpperCase());
			sitioCursoServicio.crear(nuevoSitioCurso);
			bitacora = new Bitacora(fechaCreacion, "Creación de sitio: " + nuevoSitioCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El sitio fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarSitioCurso.setEstado(estadoInactivo);
			sitioCursoServicio.editar(eliminarSitioCurso);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de sitio: " + eliminarSitioCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarSitioCurso = new SitioCurso();
			this.ponerMensajeInfo("El sitio fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public SitioCurso getNuevoSitioCurso() {
		return nuevoSitioCurso;
	}
	public void setNuevoSitioCurso(SitioCurso nuevoSitioCurso) {
		this.nuevoSitioCurso = nuevoSitioCurso;
	}
	public SitioCurso getEliminarSitioCurso() {
		return eliminarSitioCurso;
	}
	public void setEliminarSitioCurso(SitioCurso eliminarSitioCurso) {
		this.eliminarSitioCurso = eliminarSitioCurso;
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
	public boolean isEditarSitioCurso() {
		return editarSitioCurso;
	}
	public void setEditarSitioCurso(boolean editarSitioCurso) {
		this.editarSitioCurso = editarSitioCurso;
	}
	public List<SitioCurso> getListadoSitios() {
		return listadoSitios;
	}
	public void setListadoSitios(List<SitioCurso> listadoSitios) {
		this.listadoSitios = listadoSitios;
	}	

}
