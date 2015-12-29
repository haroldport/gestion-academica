package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Rol;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.servicio.ClienteServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.RolServicio;
import gestion.academica.utilitario.Crypt;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteServicio clienteServicio;
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	@EJB
	private RolServicio rolServicio;

	private Cliente nuevoCliente;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private Bitacora bitacora;
	private List<CatalogoDetalle> tiposDocumento;
	private List<CatalogoDetalle> tiposPersona;
	private List<CatalogoDetalle> ciudades;
	private static final Logger LOGGER = Logger.getLogger(ClienteBean.class
			.getName());
	private Rol rolCliente;
	private List<Cliente> listadoClientes;
	private boolean editarCliente;
	private Cliente eliminarCliente;

	@PostConstruct
	public void iniciar() {
		try {
			setEditarCliente(Boolean.FALSE);
			initValores();
			rolCliente = rolServicio.obtenerPorNemonico("RCLI");
			tiposDocumento = catalogoDetalleServicio
					.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_DOCUMENTO
							.getNemonico());
			tiposPersona = catalogoDetalleServicio
					.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_PERSONA
							.getNemonico());
			ciudades = catalogoDetalleServicio
					.obtenerPorCatalogoNemonico(CatalogoEnum.CIUDAD
							.getNemonico());
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO
					.getNemonico());
			estadoInactivo = estadoServicio
					.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	private void initValores() {
		nuevoCliente = new Cliente();
		nuevoCliente.setUsuario(new Usuario());
		nuevoCliente.setCatalogoDetalle(new CatalogoDetalle());
		nuevoCliente.setTipoPersona(new CatalogoDetalle());
		nuevoCliente.setCiudad(new CatalogoDetalle());
		listadoClientes = clienteServicio.obtenerClientes();
	}

	public void guardar() {
		if (nuevoCliente.getUsuario().getUsername() == null) {
			nuevoCliente.getUsuario().setUsername(
					nuevoCliente.getNombres().split(" ")[0].toLowerCase());
			nuevoCliente.getUsuario().setClave(
					nuevoCliente.getUsuario().getUsername());
		}
		nuevoCliente.setEstado(estadoActivo);
		nuevoCliente.setNombres(nuevoCliente.getNombres().toUpperCase());
		nuevoCliente.setDireccion(nuevoCliente.getDireccion().toUpperCase());
		nuevoCliente.getUsuario().setClave(
				Crypt.encryptMD5(nuevoCliente.getUsuario().getClave()));
		nuevoCliente.getUsuario().setRol(rolCliente);
		nuevoCliente.getUsuario().setEstado(estadoActivo);
		clienteServicio.crear(nuevoCliente);
		initValores();
	}

	public String seleccionarCliente(Cliente cliente) {
		setEliminarCliente(cliente);
		return "";
	}

	public String editarCliente() {
		try {
			setEditarCliente(false);
			nuevoCliente.setNombres(nuevoCliente.getNombres().toUpperCase());
			nuevoCliente
					.setDireccion(nuevoCliente.getDireccion().toUpperCase());
			clienteServicio.editar(nuevoCliente);
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarCliente(Cliente cliente) {
		setEditarCliente(true);
		setNuevoCliente(cliente);
		return "";
	}

	public void eliminar() {
		try {
			eliminarCliente.setEstado(estadoInactivo);
			clienteServicio.editar(eliminarCliente);
			initValores();
			eliminarCliente = new Cliente();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(Cliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
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

	public List<CatalogoDetalle> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(List<CatalogoDetalle> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public Rol getRolCliente() {
		return rolCliente;
	}

	public void setRolCliente(Rol rolCliente) {
		this.rolCliente = rolCliente;
	}

	public List<CatalogoDetalle> getTiposPersona() {
		return tiposPersona;
	}

	public void setTiposPersona(List<CatalogoDetalle> tiposPersona) {
		this.tiposPersona = tiposPersona;
	}

	public List<CatalogoDetalle> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CatalogoDetalle> ciudades) {
		this.ciudades = ciudades;
	}

	public ClienteServicio getClienteServicio() {
		return clienteServicio;
	}

	public void setClienteServicio(ClienteServicio clienteServicio) {
		this.clienteServicio = clienteServicio;
	}

	public List<Cliente> getListadoClientes() {
		return listadoClientes;
	}

	public void setListadoClientes(List<Cliente> listadoClientes) {
		this.listadoClientes = listadoClientes;
	}

	public boolean isEditarCliente() {
		return editarCliente;
	}

	public void setEditarCliente(boolean editarCliente) {
		this.editarCliente = editarCliente;
	}

	public Cliente getEliminarCliente() {
		return eliminarCliente;
	}

	public void setEliminarCliente(Cliente eliminarCliente) {
		this.eliminarCliente = eliminarCliente;
	}

}
