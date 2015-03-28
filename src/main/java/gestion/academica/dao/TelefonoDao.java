package gestion.academica.dao;

import gestion.academica.modelo.Telefono;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TelefonoDao extends Generico<Telefono> {

    @PersistenceContext
    private EntityManager em;

    public TelefonoDao() {
        super(Telefono.class);
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
	public List<Telefono> obtenerPorIdProveedor(Integer idProveedor) {
        try {
            String sql = "SELECT t FROM Telefono t WHERE t.idProveedor.idProveedor = :idProveedor ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idProveedor", idProveedor);
            List<Telefono> telefonos = query.getResultList() != null && !query.getResultList().isEmpty() ? (List<Telefono>) query.getResultList() : null;
            return telefonos;
        } catch (Exception e) {
            Logger.getLogger(TelefonoDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
