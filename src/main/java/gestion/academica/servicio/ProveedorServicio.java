package gestion.academica.servicio;

import gestion.academica.dao.ProductoProveedorDao;
import gestion.academica.dao.ProveedorDao;
import gestion.academica.dao.TelefonoDao;
import gestion.academica.modelo.ProductoProveedor;
import gestion.academica.modelo.Proveedor;
import gestion.academica.modelo.Telefono;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProveedorServicio {
	
	@EJB
	private ProveedorDao proveedorDao;
	@EJB
	private TelefonoDao telefonoDao;
	@EJB
	private ProductoProveedorDao productoProveedorDao;
	
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
	
	/**
	 * Crear proveedor con telefonos y productos
	 * @param proveedor
	 * @param listaTelefonos
	 * @param listaProductos
	 */
	public void crearProveedorConTelefonoYProductos(Proveedor proveedor, List<Telefono> listaTelefonos, List<ProductoProveedor> listaProductos){
		try {
			proveedor.setAnioFiscal(2014);
			proveedorDao.create(proveedor);
			if(listaTelefonos != null){
				for(Telefono t : listaTelefonos){
					t.setIdProveedor(proveedor);
					telefonoDao.create(t);
				}
			}
			if(listaProductos != null){
				for(ProductoProveedor p : listaProductos){
					p.setIdProveedor(proveedor);
					productoProveedorDao.create(p);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Obtener por idProveedor
     * @param idProveedor
     * @return
     */
	public List<Telefono> obtenerTelefonosPorIdProveedor(Integer idProveedor) {
    	return telefonoDao.obtenerPorIdProveedor(idProveedor);
    }
	
	/**
     * Obtener por idProveedor
     * @param idProveedor
     * @return
     */
	public List<ProductoProveedor> obtenerProductosPorIdProveedor(Integer idProveedor) {
    	return productoProveedorDao.obtenerPorIdProveedor(idProveedor);
    }
	
	/**
     * Obtener por id
     * @param idProveedor
     * @return
     */
	public Proveedor obtenerPorId(Integer idProveedor) {
		return proveedorDao.obtenerPorId(idProveedor);
	}
	
	/**
	 * Datos para loguin
	 * @param ruc
	 * @param username
	 * @param clave
	 * @return
	 */
	public Proveedor obtenerPorRucUserYClave(String ruc, String username, String clave) {
		return proveedorDao.obtenerPorRucUserYClave(ruc, username, clave);
	}

}
