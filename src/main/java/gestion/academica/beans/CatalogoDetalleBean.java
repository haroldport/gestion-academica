package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Catalogo;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Estado;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.servicio.CatalogoServicio;
import gestion.academica.servicio.EstadoServicio;
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
public class CatalogoDetalleBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private CatalogoServicio catalogoServicio;
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	
	private List<Catalogo> listaCatalogos;
	private List<CatalogoDetalle> listaCatalogoDetalle;
	private CatalogoDetalle nuevoCatalogoDetalle;
	private CatalogoDetalle eliminarCatalogoDetalle;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(CatalogoDetalleBean.class.getName());
	private Bitacora bitacora;
	private boolean editarCatalogoDetalle;
	
	@PostConstruct
	public void iniciar() {
		try {
			listaCatalogos = catalogoServicio.listarCatalogos();			
			listaCatalogoDetalle = new ArrayList<>();
			eliminarCatalogoDetalle = new CatalogoDetalle();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarCatalogoDetalle(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoCatalogoDetalle = new CatalogoDetalle();
		nuevoCatalogoDetalle.setCatalogo(new Catalogo());
		listaCatalogoDetalle = catalogoDetalleServicio.listarCatalogosDetalle();
	}
	
	public String seleccionarCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		setEliminarCatalogoDetalle(catalogoDetalle);
		return "";
	}

	public String editarCatalogoDetalle() {
		try {
			Date fechaActualizacion = new Date();
			setEditarCatalogoDetalle(false);
			nuevoCatalogoDetalle.setNombre(nuevoCatalogoDetalle.getNombre().toUpperCase());
			nuevoCatalogoDetalle.setDescripcion(nuevoCatalogoDetalle.getDescripcion().toUpperCase());
			nuevoCatalogoDetalle.setNemonico(nuevoCatalogoDetalle.getNemonico().toUpperCase());
			catalogoDetalleServicio.editar(nuevoCatalogoDetalle);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de catálogo detalle: " + nuevoCatalogoDetalle.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El catálogo detalle fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarCatalogoDetalle(CatalogoDetalle catalogoDetalle) {
		setEditarCatalogoDetalle(true);
		setNuevoCatalogoDetalle(catalogoDetalle);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoCatalogoDetalle.setEstado(estadoActivo);
			nuevoCatalogoDetalle.setNombre(nuevoCatalogoDetalle.getNombre().toUpperCase());
			nuevoCatalogoDetalle.setDescripcion(nuevoCatalogoDetalle.getDescripcion().toUpperCase());
			nuevoCatalogoDetalle.setNemonico(nuevoCatalogoDetalle.getNemonico().toUpperCase());
			catalogoDetalleServicio.crear(nuevoCatalogoDetalle);
			bitacora = new Bitacora(fechaCreacion, "Creación de catálogo detalle: " + nuevoCatalogoDetalle.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El catálogo detalle fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarCatalogoDetalle.setEstado(estadoInactivo);
			catalogoDetalleServicio.editar(eliminarCatalogoDetalle);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de catálogo detalle: " + eliminarCatalogoDetalle.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarCatalogoDetalle = new CatalogoDetalle();
			this.ponerMensajeInfo("El catálogo detalle fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public List<Catalogo> getListaCatalogos() {
		return listaCatalogos;
	}
	public void setListaCatalogos(List<Catalogo> listaCatalogos) {
		this.listaCatalogos = listaCatalogos;
	}
	public List<CatalogoDetalle> getListaCatalogoDetalle() {
		return listaCatalogoDetalle;
	}
	public void setListaCatalogoDetalle(List<CatalogoDetalle> listaCatalogoDetalle) {
		this.listaCatalogoDetalle = listaCatalogoDetalle;
	}
	public CatalogoDetalle getNuevoCatalogoDetalle() {
		return nuevoCatalogoDetalle;
	}
	public void setNuevoCatalogoDetalle(CatalogoDetalle nuevoCatalogoDetalle) {
		this.nuevoCatalogoDetalle = nuevoCatalogoDetalle;
	}
	public CatalogoDetalle getEliminarCatalogoDetalle() {
		return eliminarCatalogoDetalle;
	}
	public void setEliminarCatalogoDetalle(CatalogoDetalle eliminarCatalogoDetalle) {
		this.eliminarCatalogoDetalle = eliminarCatalogoDetalle;
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
	public boolean isEditarCatalogoDetalle() {
		return editarCatalogoDetalle;
	}
	public void setEditarCatalogoDetalle(boolean editarCatalogoDetalle) {
		this.editarCatalogoDetalle = editarCatalogoDetalle;
	}
}
