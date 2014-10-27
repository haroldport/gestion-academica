package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Rol;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.RolServicio;
import gestion.academica.servicio.UsuarioServicio;
import gestion.academica.utilitario.Crypt;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioServicio usuarioServicio;
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private RolServicio rolServicio;
	private List<Usuario> listaUsuarios;
	private Usuario nuevoUsuario;
	private boolean editarUsuario;
	private Usuario eliminarUsuario;
	private List<Rol> listaRoles;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(UsuarioBean.class
			.getName());

	@PostConstruct
	public void iniciar() {
		try {
			listaUsuarios = new ArrayList<>();
			eliminarUsuario = new Usuario();
			listaRoles = rolServicio.listarRoles();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			this.setEditarUsuario(Boolean.FALSE);
			initValores();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	private void initValores() throws Exception {
		nuevoUsuario = new Usuario();
		nuevoUsuario.setRol(new Rol());
		obtenerlistaUsuarios();
	}

	public void obtenerlistaUsuarios() throws Exception {
		this.listaUsuarios = usuarioServicio.listarUsuarios();
	}

	public String seleccionarUsuario(Usuario usuario) {
		setEliminarUsuario(usuario);
		return "";
	}

	public String editarUsuario() {
		try {
			setEditarUsuario(false);
			usuarioServicio.actualizar(nuevoUsuario);
			initValores();
			this.ponerMensajeInfo("El usuario fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarUsuario(Usuario usuario) {
		setEditarUsuario(true);
		setNuevoUsuario(usuario);
		return "";
	}

	public void guardar() {
		try {
			nuevoUsuario.setEstado(estadoActivo);
			nuevoUsuario.setClave(Crypt.encryptMD5(nuevoUsuario.getClave()));
			usuarioServicio.ingresar(nuevoUsuario);
			this.ponerMensajeInfo("El usuario fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			eliminarUsuario.setEstado(estadoInactivo);
			usuarioServicio.actualizar(eliminarUsuario);			
			obtenerlistaUsuarios();
			eliminarUsuario = new Usuario();
			this.ponerMensajeInfo("El usuario fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public boolean iseditarUsuario() {
		return editarUsuario;
	}

	public void setEditarUsuario(boolean editarUsuario) {
		this.editarUsuario = editarUsuario;
	}

	public Usuario geteliminarUsuario() {
		return eliminarUsuario;
	}

	public void setEliminarUsuario(Usuario eliminarUsuario) {
		this.eliminarUsuario = eliminarUsuario;
	}

	public Usuario getnuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(Usuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}

	public List<Usuario> getlistaUsuarios() {
		return listaUsuarios;
	}

	public void setlistaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
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

}
