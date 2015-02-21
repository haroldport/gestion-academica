package gestion.academica.dao;

import gestion.academica.modelo.Matricula;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MatriculaDao extends Generico<Matricula> {

    @PersistenceContext
    private EntityManager em;

    public MatriculaDao() {
        super(Matricula.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
