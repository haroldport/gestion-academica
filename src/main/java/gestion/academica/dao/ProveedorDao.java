package gestion.academica.dao;

import gestion.academica.modelo.Proveedor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProveedorDao extends Generico<Proveedor> {

    @PersistenceContext
    private EntityManager em;

    public ProveedorDao() {
        super(Proveedor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtener por id
     * @param idProveedor
     * @return
     */
	public Proveedor obtenerPorId(Integer idProveedor) {
        try {
            String sql = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idProveedor", idProveedor);
            Proveedor proveedor = query.getResultList() != null && !query.getResultList().isEmpty() ? (Proveedor) query.getResultList().get(0) : null;
            return proveedor;
        } catch (Exception e) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
	
	/**
	 * Datos para loguin
	 * @param ruc
	 * @param username
	 * @param clave
	 * @return
	 */
	public Proveedor obtenerPorRucUserYClave(String ruc, String username, String clave) {
        try {
            String sql = "SELECT p FROM Proveedor p WHERE p.ruc = :ruc "
            		+ "AND p.usuario = :username AND p.clave = :clave";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("ruc", ruc);
            query.setParameter("username", username);
            query.setParameter("clave", clave);
            Proveedor proveedor = query.getResultList() != null && !query.getResultList().isEmpty() ? (Proveedor) query.getResultList().get(0) : null;
            return proveedor;
        } catch (Exception e) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
