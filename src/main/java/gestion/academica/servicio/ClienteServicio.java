package gestion.academica.servicio;

import gestion.academica.dao.ClienteDao;
import gestion.academica.dao.UsuarioDao;
import gestion.academica.modelo.Cliente;

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

}
