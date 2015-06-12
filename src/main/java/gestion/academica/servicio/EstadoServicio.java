package gestion.academica.servicio;

import gestion.academica.dao.EstadoDao;
import gestion.academica.modelo.Estado;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EstadoServicio {
	
	@EJB
	private EstadoDao estadoDao;
	
	/**
     * Buscar estado por su nemonico
     * @param nemonico
     * @return
     * @throws Exception 
     */
	public Estado buscarPorNemonico(String nemonico) throws Exception {
    	return estadoDao.buscarPorNemonico(nemonico);
    }

}
