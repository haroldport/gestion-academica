package gestion.academica.dao;

import gestion.academica.modelo.Rol;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Dao que contiene los servicios de la entidad Rol.
 *
 * @author Jorge Rivera.
 * @version $Revision: 1.0
 *
 */
@LocalBean
@Stateless
public class RolDao extends Generico<Rol> {

    @PersistenceContext
    private EntityManager em;

    public RolDao() {
        super(Rol.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Servicio que lista todas los Roles.
     *
     * @return List<Rol>
     */
    @SuppressWarnings("unchecked")
	public List<Rol> listarRoles() {
        String sql = "SELECT r FROM Rol r WHERE r.estado.idEstado = 1 ORDER BY r.nombre";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

    /**
     * Servicio que retorna un Catalogo dado un nemonico.
     *
     * @param nemonico
     *
     * @param Catalogo
     */
    public Rol obtenerPorNemonico(String nemonico) {
        try {
            Query query = this.em.createQuery("SELECT r FROM Rol r WHERE r.nemonico = :nemonico");
            query.setParameter("nemonico", nemonico);
            return query.getResultList() != null && !query.getResultList().isEmpty() ? (Rol) query.getResultList().get(0) : null;
        } catch (Exception e) {
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
