package gestion.academica.dao;

import gestion.academica.modelo.Estudiante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstudianteDao extends Generico<Estudiante> {

    @PersistenceContext
    private EntityManager em;

    public EstudianteDao() {
        super(Estudiante.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
