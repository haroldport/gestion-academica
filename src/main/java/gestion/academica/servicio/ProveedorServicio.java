package gestion.academica.servicio;

import gestion.academica.dao.ProveedorDao;
import gestion.academica.modelo.Proveedor;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProveedorServicio {
	
	@EJB
	private ProveedorDao proveedorDao;
	
	/**
	 * Crear un nuevo proveedor
	 * @param proveedor
	 */
	public void crear(Proveedor proveedor){
		try {
			proveedorDao.create(proveedor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un proveedor existente
	 * @param proveedor
	 */
	public void editar(Proveedor proveedor){
		try {
			proveedorDao.edit(proveedor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
