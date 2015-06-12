package gestion.academica.servicio;

import gestion.academica.dao.BitacoraDao;
import gestion.academica.modelo.Bitacora;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BitacoraServicio {
	
	@EJB
	private BitacoraDao bitacoraDao;
	
	/**
	 * Crear un nuevo registro en la bitacora
	 * @param bitacora
	 */
	public void crear(Bitacora bitacora){
		try{
			bitacoraDao.create(bitacora);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un registro existente en la bitacora
	 * @param bitacora
	 */
	public void editar(Bitacora bitacora){
		try{
			bitacoraDao.edit(bitacora);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
