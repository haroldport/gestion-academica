package gestion.academica.dao;

import gestion.academica.modelo.Proveedor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
