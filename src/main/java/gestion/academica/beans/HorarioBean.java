package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Horario;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.HorarioServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.ArrayList;
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
public class HorarioBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private HorarioServicio horarioServicio;
	
	private List<Horario> listaHorarios;
	private Horario nuevoHorario;
	private Horario eliminarHorario;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(HorarioBean.class.getName());
	private Bitacora bitacora;
	private boolean editarHorario;
	
	@PostConstruct
	public void iniciar() {
		try {
			listaHorarios = new ArrayList<>();			
			eliminarHorario = new Horario();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarHorario(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoHorario = new Horario();
		listaHorarios = horarioServicio.listarHorarios();
	}
	
	public String seleccionarHorario(Horario Horario) {
		setEliminarHorario(Horario);
		return "";
	}

	public String editarHorario() {
		try {
			Date fechaActualizacion = new Date();
			setEditarHorario(false);
			nuevoHorario.setDias(nuevoHorario.getDias().toUpperCase());
			horarioServicio.editar(nuevoHorario);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de horario: " + nuevoHorario.getDias(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El horario fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarHorario(Horario Horario) {
		setEditarHorario(true);
		setNuevoHorario(Horario);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoHorario.setEstado(estadoActivo);
			nuevoHorario.setDias(nuevoHorario.getDias().toUpperCase());
			horarioServicio.crear(nuevoHorario);
			bitacora = new Bitacora(fechaCreacion, "Creación de horario: " + nuevoHorario.getDias(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El horario fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarHorario.setEstado(estadoInactivo);
			horarioServicio.editar(eliminarHorario);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de horario: " + eliminarHorario.getDias(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarHorario = new Horario();
			this.ponerMensajeInfo("El horario fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public List<Horario> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public Horario getNuevoHorario() {
		return nuevoHorario;
	}

	public void setNuevoHorario(Horario nuevoHorario) {
		this.nuevoHorario = nuevoHorario;
	}

	public Horario getEliminarHorario() {
		return eliminarHorario;
	}

	public void setEliminarHorario(Horario eliminarHorario) {
		this.eliminarHorario = eliminarHorario;
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

	public boolean isEditarHorario() {
		return editarHorario;
	}

	public void setEditarHorario(boolean editarHorario) {
		this.editarHorario = editarHorario;
	}

}
