package gestion.academica.beans;

import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.UsuarioServicio;
import gestion.academica.utilitario.Crypt;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

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
	
	private String username;
    private String password;
    private Usuario usuario;
    
    @PostConstruct
    public void init() {
        usuario = new Usuario();
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
        	 return "/principal.xhtml?faces-redirect=true";
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "";
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

}
