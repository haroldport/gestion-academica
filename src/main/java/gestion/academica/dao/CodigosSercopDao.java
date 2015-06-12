package gestion.academica.dao;

import gestion.academica.modelo.CodigosSercop;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CodigosSercopDao  extends Generico<CodigosSercop>{
	
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodigosSercopDao() {
        super(CodigosSercop.class);
    }
    
    /**
     * Buscar por palabra clave
     * @param palabraClave
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<CodigosSercop> buscarPorPalabraClave(String palabraClave) throws Exception {
        List<CodigosSercop> result = new ArrayList<>();
        String jpql = "SELECT c FROM CodigosSercop c "
                + "WHERE UPPER(c.descripcion) LIKE :palabraClave "
                + "AND c.nivel = 5";
        Query query = em.createQuery(jpql);
        query.setParameter("palabraClave", "%" + palabraClave.toUpperCase() + "%");
        result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }
    
    /**
     * Buscar por codigo de lote
     * @param codigoLote
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<CodigosSercop> buscarPorCodigoLote(String codigoLote) throws Exception {
        List<CodigosSercop> result = new ArrayList<>();
        String jpql = "SELECT c FROM CodigosSercop c "
                + "WHERE c.codigo = :codigoLote "
                + "AND c.nivel = 5";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoLote", codigoLote);
        result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

}
