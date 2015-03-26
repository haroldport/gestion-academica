package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.CodigosSercopDao;
import gestion.academica.modelo.CodigosSercop;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CodigosSercopServicio {
	
	@EJB
	private CodigosSercopDao codigosSercopDao;
	
	/**
     * Buscar por palabra clave
     * @param palabraClave
     * @return
     * @throws Exception
     */
	public List<CodigosSercop> buscarPorPalabraClave(String palabraClave) throws Exception {
    	return codigosSercopDao.buscarPorPalabraClave(palabraClave);
    }
	
	/**
     * Buscar por codigo de lote
     * @param codigoLote
     * @return
     * @throws Exception
     */
	public List<CodigosSercop> buscarPorCodigoLote(String codigoLote) throws Exception {
    	return codigosSercopDao.buscarPorCodigoLote(codigoLote);
    }

}
