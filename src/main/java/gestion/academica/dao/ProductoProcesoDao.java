package gestion.academica.dao;

import gestion.academica.modelo.ProductoProceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductoProcesoDao extends Generico<ProductoProceso> {

    @PersistenceContext
    private EntityManager em;

    public ProductoProcesoDao() {
        super(ProductoProceso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
