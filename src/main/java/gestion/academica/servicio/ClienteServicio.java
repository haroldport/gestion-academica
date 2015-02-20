package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.ClienteDao;
import gestion.academica.dao.UsuarioDao;
import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Usuario;

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
	public void crear(Cliente cliente){
		try{
			usuarioDao.create(cliente.getUsuario());
			cliente.setUsuario(cliente.getUsuario());
			clienteDao.create(cliente);
		}catch(Exception e){
			e.printStackTrace();
		}
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
