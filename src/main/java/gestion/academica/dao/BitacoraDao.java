package gestion.academica.dao;

import gestion.academica.modelo.Bitacora;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BitacoraDao  extends Generico<Bitacora> {

    @PersistenceContext
    private EntityManager em;

    public BitacoraDao() {
        super(Bitacora.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
