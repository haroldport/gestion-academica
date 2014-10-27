package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Rol;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.RolServicio;
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
public class RolBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private RolServicio rolServicio;
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	private List<Rol> listaRoles;
    private Rol nuevoRol;
    private boolean editarRol;
    private Rol eliminarRol;
    private Estado estadoActivo;
	private Estado estadoInactivo;
	private Bitacora bitacora;

	@PostConstruct
    public void iniciar() {
        try {
        	listaRoles = new ArrayList<Rol>();
        	nuevoRol = new Rol();
        	estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
            this.setEditarRol(Boolean.FALSE);
            obtenerListaRoles();
        } catch (Exception ex) {
            Logger.getLogger(RolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void obtenerListaRoles(){
		try {
            this.listaRoles = rolServicio.listarRoles();
        } catch (Exception ex) {
            Logger.getLogger(RolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public String seleccionarRol(Rol rol) {
        setEliminarRol(rol);
        return "";
    }

    public String editarRol() {
        try {
        	Date fechaActualizacion = new Date();
            setEditarRol(false);
            nuevoRol.setNombre(nuevoRol.getNombre().toUpperCase());
            nuevoRol.setDescripcion(nuevoRol.getDescripcion().toUpperCase());
            nuevoRol.setNemonico(nuevoRol.getNemonico().toUpperCase());
            rolServicio.actualizar(nuevoRol);
            bitacora = new Bitacora(fechaActualizacion, "Modificación de rol: " + nuevoRol.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
            nuevoRol = new Rol();
            obtenerListaRoles();
            this.ponerMensajeInfo("El rol fue actualizado correctamente", "");
        } catch (Exception e) {
            Logger.getLogger(RolBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return "";
    }

    public String editarRol(Rol rol) {
        setEditarRol(true);
        setNuevoRol(rol);
        return "";
    }

    public void guardar() {
        try {
        	Date fechaCreacion = new Date();
            nuevoRol.setEstado(estadoActivo);
            nuevoRol.setNombre(nuevoRol.getNombre().toUpperCase());
            nuevoRol.setDescripcion(nuevoRol.getDescripcion().toUpperCase());
            nuevoRol.setNemonico(nuevoRol.getNemonico().toUpperCase());
            rolServicio.ingresar(nuevoRol);
            bitacora = new Bitacora(fechaCreacion, "Creación de rol: " + nuevoRol.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
            nuevoRol = new Rol();            
            obtenerListaRoles();
            this.ponerMensajeInfo("El rol fue creado correctamente", "");
        } catch (Exception e) {
            Logger.getLogger(RolBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void eliminar() {
        try {
        	Date fechaEliminacion = new Date();
            eliminarRol.setEstado(estadoInactivo);
            rolServicio.actualizar(eliminarRol);
            bitacora = new Bitacora(fechaEliminacion, "Eliminación de rol: " + eliminarRol.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
            obtenerListaRoles();
            this.ponerMensajeInfo("El rol fue eliminado correctamente", "");
        } catch (Exception e) {
            Logger.getLogger(RolBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Rol getNuevoRol() {
		return nuevoRol;
	}

	public void setNuevoRol(Rol nuevoRol) {
		this.nuevoRol = nuevoRol;
	}

	public boolean isEditarRol() {
		return editarRol;
	}

	public void setEditarRol(boolean editarRol) {
		this.editarRol = editarRol;
	}

	public Rol getEliminarRol() {
		return eliminarRol;
	}

	public void setEliminarRol(Rol eliminarRol) {
		this.eliminarRol = eliminarRol;
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

}
