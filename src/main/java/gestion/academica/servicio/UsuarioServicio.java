package gestion.academica.servicio;

import gestion.academica.dao.UsuarioDao;
import gestion.academica.modelo.Usuario;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class UsuarioServicio {
	
	@EJB
	private UsuarioDao usuarioDao;
	
	/**
     * Obtener por username y clave
     * @param username
     * @param clave
     * @return
     */
    public Usuario obtenerUsuarioPorUsernameYClave(String username, String clave) {
    	return usuarioDao.obtenerUsuarioPorUsernameYClave(username, clave);
    }
    
    /**
     * Servicio que lista todas los Usuarios.
     *
     * @return List<Usuario>
     */
	public List<Usuario> listarUsuarios() {
    	return usuarioDao.listarUsuarios();
    }

}
