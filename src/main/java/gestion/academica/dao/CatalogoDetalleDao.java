package gestion.academica.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gestion.academica.modelo.CatalogoDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CatalogoDetalleDao extends Generico<CatalogoDetalle> {

    @PersistenceContext
    private EntityManager em;

    public CatalogoDetalleDao() {
        super(CatalogoDetalle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar todos los catalogos detalle
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<CatalogoDetalle> listarCatalogosDetalle() {
        String sql = "SELECT c FROM CatalogoDetalle c WHERE c.estado.idEstado = 1 ORDER BY c.idCatalogoDetalle";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
     
    /**
     * Buscar detalles por nemonico de catalogo
     * @param nemonico
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<CatalogoDetalle> obtenerPorCatalogoNemonico(String nemonico) {
        try {
            String sql = "SELECT c FROM CatalogoDetalle c WHERE c.estado.idEstado = 1 "
            		+ "AND c.catalogo.nemonico = :nemonico";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nemonico", nemonico);
            List<CatalogoDetalle> catalogosDetalle = query.getResultList() != null && !query.getResultList().isEmpty() ? (List<CatalogoDetalle>) query.getResultList() : null;
            return catalogosDetalle;
        } catch (Exception e) {
            Logger.getLogger(CatalogoDetalleDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
