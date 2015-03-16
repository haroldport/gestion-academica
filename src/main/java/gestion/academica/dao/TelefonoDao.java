package gestion.academica.dao;

import gestion.academica.modelo.Telefono;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
