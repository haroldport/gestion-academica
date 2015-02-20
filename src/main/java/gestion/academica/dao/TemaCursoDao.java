package gestion.academica.dao;

import gestion.academica.modelo.InformacionCurso;
import gestion.academica.modelo.TemaCurso;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TemaCursoDao extends Generico<TemaCurso> {

    @PersistenceContext
    private EntityManager em;

    public TemaCursoDao() {
        super(TemaCurso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar todos los temas padres
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<TemaCurso> listarTemasCursoPadres() {
        String sql = "SELECT t FROM TemaCurso t WHERE t.estado.idEstado = 1 AND t.temaCurso is null ORDER BY t.idTemaCurso";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
    
    /**
     * Listar temas padres en base a un curso
     * @param informacionCurso
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<TemaCurso> listarTemasPadresPorCurso(InformacionCurso informacionCurso) {
        String sql = "SELECT t FROM TemaCurso t WHERE t.estado.idEstado = 1 AND t.temaCurso is null AND t.informacionCurso = :informacionCurso ORDER BY t.idTemaCurso";
        return this.getEntityManager().createQuery(sql).setParameter("informacionCurso", informacionCurso).getResultList();
    }

}
