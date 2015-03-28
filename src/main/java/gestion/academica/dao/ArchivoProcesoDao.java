package gestion.academica.dao;

import gestion.academica.modelo.ArchivoProceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ArchivoProcesoDao extends Generico<ArchivoProceso> {

    @PersistenceContext
    private EntityManager em;

    public ArchivoProcesoDao() {
        super(ArchivoProceso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
