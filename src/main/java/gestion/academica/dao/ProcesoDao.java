package gestion.academica.dao;

import gestion.academica.modelo.Proceso;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProcesoDao extends Generico<Proceso> {

    @PersistenceContext
    private EntityManager em;

    public ProcesoDao() {
        super(Proceso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtener por codigo de proceso
     * @param codigoProceso
     * @return
     */
    public Proceso obtenerPorCodigoProceso(String codigoProceso) {
        try {
            String sql = "SELECT p FROM Proceso p WHERE p.codigoProceso = :codigoProceso ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("codigoProceso", codigoProceso);
            Proceso proceso = query.getResultList() != null && !query.getResultList().isEmpty() ? (Proceso) query.getResultList().get(0) : null;
            return proceso;
        } catch (Exception e) {
            Logger.getLogger(ProcesoDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
