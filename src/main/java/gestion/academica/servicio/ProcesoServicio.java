package gestion.academica.servicio;

import gestion.academica.dao.ProcesoDao;
import gestion.academica.modelo.Proceso;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProcesoServicio {
	
	@EJB
	private ProcesoDao procesoDao;
	
	/**
     * Obtener por codigo de proceso
     * @param codigoProceso
     * @return
     */
    public Proceso obtenerPorCodigoProceso(String codigoProceso) {
    	return procesoDao.obtenerPorCodigoProceso(codigoProceso);
    }

}
