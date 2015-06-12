package gestion.academica.beans;

import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Proveedor;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.ClienteServicio;
import gestion.academica.servicio.ProveedorServicio;
import gestion.academica.servicio.UsuarioServicio;
import gestion.academica.utilitario.Crypt;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class LoguinIncopBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProveedorServicio proveedorServicio;
	@EJB
	private UsuarioServicio usuarioServicio;
	@EJB
	private ClienteServicio clienteServicio;
	
	private Proveedor proveedorLogueado;
	private String ruc;
	private String username;
	private String clave;
	private Usuario usuario;
	private Cliente cliente;
	
	@PostConstruct
	public void iniciar() {
		try {
			iniciarVariables();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String descargarArchivo(String nombreArchivo){
    	return nombreArchivo;
    }
	
	private void iniciarVariables(){
		setProveedorLogueado(new Proveedor());
		setRuc("");
		setUsername("");
		setClave("");
	}
	
	public String verRegistroProveedores(){
		return "/faces/paginas/procesos/registroProveedores.xhtml?faces-redirect=true";
	}
	
	public String crearProceso(){
		return "/faces/paginas/procesos/creacionProceso.xhtml?faces-redirect=true";
	}
	
	public String ingresoPortal(){
		proveedorLogueado = proveedorServicio.obtenerPorRucUserYClave(ruc, username, clave);
		if(proveedorLogueado != null){
			return "/faces/paginas/procesos/principalProveedores.xhtml?faces-redirect=true";
		}else{
			usuario = usuarioServicio.obtenerUsuarioPorUsernameYClave(username, Crypt.encryptMD5(clave));
			if(usuario != null){
				cliente = clienteServicio.obtenerPorUsuario(usuario);
				return "/faces/paginas/procesos/principalContratantes.xhtml?faces-redirect=true";
			}
		}
		return "";
	}
	
	public String regresarLogin(){
		iniciarVariables();
		return "/faces/paginas/procesos/principal.xhtml?faces-redirect=true";
	}

	public Proveedor getProveedorLogueado() {
		return proveedorLogueado;
	}

	public void setProveedorLogueado(Proveedor proveedorLogueado) {
		this.proveedorLogueado = proveedorLogueado;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
