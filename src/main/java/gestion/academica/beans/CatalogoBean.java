package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Catalogo;
import gestion.academica.modelo.Estado;
import gestion.academica.servicio.BitacoraServicio;
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
public class CatalogoBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private CatalogoServicio catalogoServicio;
	
	private List<Catalogo> listaCatalogos;
	private Catalogo nuevoCatalogo;
	private Catalogo eliminarCatalogo;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(CatalogoBean.class.getName());
	private Bitacora bitacora;
	private boolean editarCatalogo;
	
	@PostConstruct
	public void iniciar() {
		try {
			listaCatalogos = new ArrayList<>();			
			eliminarCatalogo = new Catalogo();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			initValores();
			this.setEditarCatalogo(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoCatalogo = new Catalogo();
		listaCatalogos = catalogoServicio.listarCatalogos();
	}
	
	public String seleccionarCatalogo(Catalogo catalogo) {
		setEliminarCatalogo(catalogo);
		return "";
	}

	public String editarCatalogo() {
		try {
			Date fechaActualizacion = new Date();
			setEditarCatalogo(false);
			nuevoCatalogo.setNombre(nuevoCatalogo.getNombre().toUpperCase());
			nuevoCatalogo.setDescripcion(nuevoCatalogo.getDescripcion().toUpperCase());
			nuevoCatalogo.setNemonico(nuevoCatalogo.getNemonico().toUpperCase());
			catalogoServicio.editar(nuevoCatalogo);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de catálogo: " + nuevoCatalogo.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El catálogo fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarCatalogo(Catalogo catalogo) {
		setEditarCatalogo(true);
		setNuevoCatalogo(catalogo);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevoCatalogo.setEstado(estadoActivo);
			nuevoCatalogo.setNombre(nuevoCatalogo.getNombre().toUpperCase());
			nuevoCatalogo.setDescripcion(nuevoCatalogo.getDescripcion().toUpperCase());
			nuevoCatalogo.setNemonico(nuevoCatalogo.getNemonico().toUpperCase());
			catalogoServicio.crear(nuevoCatalogo);
			bitacora = new Bitacora(fechaCreacion, "Creación de catálogo: " + nuevoCatalogo.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El catálogo fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarCatalogo.setEstado(estadoInactivo);
			catalogoServicio.editar(eliminarCatalogo);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de catálogo: " + eliminarCatalogo.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarCatalogo = new Catalogo();
			this.ponerMensajeInfo("El catálogo fue eliminado correctamente", "");
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
	
	public Catalogo getNuevoCatalogo() {
		return nuevoCatalogo;
	}
	
	public void setNuevoCatalogo(Catalogo nuevoCatalogo) {
		this.nuevoCatalogo = nuevoCatalogo;
	}
	
	public Catalogo getEliminarCatalogo() {
		return eliminarCatalogo;
	}
	
	public void setEliminarCatalogo(Catalogo eliminarCatalogo) {
		this.eliminarCatalogo = eliminarCatalogo;
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
	
	public boolean isEditarCatalogo() {
		return editarCatalogo;
	}
	
	public void setEditarCatalogo(boolean editarCatalogo) {
		this.editarCatalogo = editarCatalogo;
	}
	
}
