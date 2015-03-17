package gestion.academica.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gestion.academica.modelo.ProductoProveedor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProductoProveedorDao extends Generico<ProductoProveedor> {

    @PersistenceContext
    private EntityManager em;

    public ProductoProveedorDao() {
        super(ProductoProveedor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtener por idProveedor
     * @param idProveedor
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<ProductoProveedor> obtenerPorIdProveedor(Integer idProveedor) {
        try {
            String sql = "SELECT p FROM ProductoProveedor p WHERE p.idProveedor.idProveedor = :idProveedor ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idProveedor", idProveedor);
            List<ProductoProveedor> productos = query.getResultList() != null && !query.getResultList().isEmpty() ? (List<ProductoProveedor>) query.getResultList() : null;
            return productos;
        } catch (Exception e) {
            Logger.getLogger(ProductoProveedorDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
