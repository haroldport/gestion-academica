package gestion.academica.beans;

import gestion.academica.modelo.Acceso;
import gestion.academica.modelo.AccesoRol;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.AccesoServicio;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.UsuarioServicio;
import gestion.academica.utilitario.Crypt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioServicio usuarioServicio;
	@EJB
	private AccesoServicio accesoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	
	private String username;
    private String password;
    private Usuario usuario;
    private MenuModel menu;
    private List<AccesoRol> listaAccesoRol;
    private String claveAnterior;
	private String claveActual;
	private String claveActualRepetida;
	private Bitacora bitacora;
	private Usuario usuarioRegistro;
	
	@ManagedProperty(value = "#{clienteBean}")
    private ClienteBean clienteBean;
    
    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuarioRegistro = usuarioServicio.obtenerUsuarioPorUsername("usuario_registro");
        listaAccesoRol = new ArrayList<>();
    }
    
	public String login(){
    	System.out.println("Ingreso Login");
        FacesMessage msg;
        try {
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
            AuthenticationManager am = (AuthenticationManager) wac.getBean("authenticationManager");
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), getPassword());

            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            System.out.println("si es valido");
            Collection<GrantedAuthority> coll = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority grantedAuthority : coll) {
                System.out.println("ROL: " + grantedAuthority.getAuthority());
            }
            setUsuario(usuarioServicio.obtenerUsuarioPorUsernameYClave(this.getUsername(), Crypt.encryptMD5(this.getPassword())));
            return "/index.xhtml?faces-redirect=true";
        } catch (AuthenticationException e) {
            System.out.println("No es valido");
            Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, e);
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso denegado", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }
    }
	
	public String verPrincipal() {
        try {
        	listaAccesoRol = new ArrayList<>();
        	this.listaAccesoRol.addAll(accesoServicio.obtenerAccesoPorRol(this.getUsuario().getRol(), "MODULO"));
        	 return "/principal.xhtml?faces-redirect=true";
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "";
    }
	
	public String navegar(AccesoRol accesoRol) {
        List<AccesoRol> listaAccesoRolMenu = new ArrayList<>();
        listaAccesoRolMenu.addAll(accesoServicio.obtenerAccesoPorRolModulo(usuario.getRol(), accesoRol.getAcceso()));
        this.menu = new DefaultMenuModel();
        for (AccesoRol menuDto : listaAccesoRolMenu) {
            if (!menuDto.getAcceso().getTipo().equals("PAGINA")) {
            	DefaultSubMenu submenu = new DefaultSubMenu();
            	submenu.setLabel(menuDto.getAcceso().getEtiqueta());
            	submenu.setId("manuId" + menuDto.getAcceso().getIdAcceso().intValue());
            	submenu.setIcon("ui-icon-circle-plus");
                cargarItems1(menuDto.getAcceso(), submenu, null);
                menu.getElements().add(submenu);
            }
        }
        System.out.println(accesoRol.getAcceso().getUrl());
        return accesoRol.getAcceso().getUrl() + "?faces-redirect=true";
    }

    @SuppressWarnings("unchecked")
	private DefaultMenuItem cargarItems1(Acceso acceso, Submenu menuPadre, DefaultMenuItem menuItem) {
        for (Acceso menuDto : acceso.getAccesos()) {
            if (menuDto.getAcceso() != null && menuDto.getEstado().getIdEstado() == 1) {
                if (menuDto.getTipo().equals("PAGINA")) {
                	DefaultMenuItem menuItem1 = new DefaultMenuItem();
                    menuItem1.setValue(menuDto.getEtiqueta());
                    menuItem1.setUrl(menuDto.getUrl());
                    menuItem1.setId("manuId" + menuDto.getIdAcceso().intValue());
                    menuItem1.setAjax(false);
                    menuPadre.getElements().add(menuItem1);
                } else {
                	DefaultSubMenu submenuHijo = new DefaultSubMenu();
                    submenuHijo.setLabel(menuDto.getEtiqueta());
                    submenuHijo.setId("manuId" + menuDto.getIdAcceso().intValue());
                    menuPadre.getElements().add(submenuHijo);
                    DefaultMenuItem menues = cargarItems1(menuDto, submenuHijo, menuItem);
                    if (menues != null) {
                        submenuHijo.getElements().add(menues);
                    }
                }
            }
        }
        return menuItem;
    }
    
    public void cambiarClave() throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean clave = false;
		if (Crypt.encryptMD5(claveAnterior).equals(this.usuario.getClave())) {			
			if(claveActual.equals(claveActualRepetida)){
				clave = true;
				this.usuario.setClave(Crypt.encryptMD5(claveActual));
				usuarioServicio.actualizar(this.usuario);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Confirmación!! La clave fue cambiada éxitosamente", "");
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!! La nueva clave y la confirmación no coinciden",
						"");
			}			
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!! La clave anterior es incorrecta",
					"");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("clave", clave);
	}
    
    public void registrar(){
    	RequestContext context = RequestContext.getCurrentInstance();
    	FacesMessage msg = null;
    	boolean registro = false;
    	Date fechaCreacion = new Date();
    	try{
    		String nombreCliente = clienteBean.getNuevoCliente().getNombres();
    		clienteBean.guardar();
        	bitacora = new Bitacora(fechaCreacion, "Creación de cliente: " + nombreCliente, usuarioRegistro);
            bitacoraServicio.crear(bitacora);
            registro = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
    				"Confirmación!! Registro realizado con éxito", "");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    	context.addCallbackParam("registro", registro);
    }
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MenuModel getMenu() {
		return menu;
	}

	public void setMenu(MenuModel menu) {
		this.menu = menu;
	}

	public List<AccesoRol> getListaAccesoRol() {
		return listaAccesoRol;
	}

	public void setListaAccesoRol(List<AccesoRol> listaAccesoRol) {
		this.listaAccesoRol = listaAccesoRol;
	}

	public String getClaveAnterior() {
		return claveAnterior;
	}

	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = claveAnterior;
	}

	public String getClaveActual() {
		return claveActual;
	}

	public void setClaveActual(String claveActual) {
		this.claveActual = claveActual;
	}

	public String getClaveActualRepetida() {
		return claveActualRepetida;
	}

	public void setClaveActualRepetida(String claveActualRepetida) {
		this.claveActualRepetida = claveActualRepetida;
	}

	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Bitacora getBitacora() {
		return bitacora;
	}

	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}
