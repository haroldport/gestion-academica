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

}
