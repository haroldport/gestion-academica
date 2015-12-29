package gestion.academica.servicio;

import gestion.academica.dao.ClienteDao;
import gestion.academica.dao.UsuarioDao;
import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Usuario;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ClienteServicio {
	
	@EJB
	private ClienteDao clienteDao;
	
	@EJB
	private UsuarioDao usuarioDao;
	
	/**
	 * Crear un nuevo cliente
	 * @param cliente
	 */
	public String crear(Cliente cliente){
		String result = null;
		try{
			usuarioDao.create(cliente.getUsuario());
			cliente.setUsuario(cliente.getUsuario());
			clienteDao.create(cliente);
		}catch(Exception e){
			String message = getStackTrace(e);
    		if(message.contains("uq_username")) result = "Error!! Ya existe el nombre de usuario";
    		else if(message.contains("uq_documento") || message.contains("detached entity passed to persist")) result = "Error!! Ya existe ua persona registrada con el mismo tipo y No. de documento";
    		else result = "Error!! Ocurrio un error al registrar la persona";
		}
		return result;
	}
	
	private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
	
	/**
	 * Editar un cliente existente
	 * @param cliente
	 */
	public void editar(Cliente cliente){
		try{
			clienteDao.edit(cliente);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Obtener por usuario
     * @param usuario
     * @return
     */
    public Cliente obtenerPorUsuario(Usuario usuario) {
    	return clienteDao.obtenerPorUsuario(usuario);
    }
    
    /**
     * Obtener clientes activos
     * @return
     */
	public List<Cliente> obtenerClientes() {
    	return clienteDao.obtenerClientes();
    }

}
