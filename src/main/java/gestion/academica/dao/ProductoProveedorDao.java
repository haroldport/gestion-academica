package gestion.academica.dao;

import gestion.academica.modelo.ProductoProveedor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
