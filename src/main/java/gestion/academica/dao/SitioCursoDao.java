package gestion.academica.dao;

import java.util.List;

import gestion.academica.modelo.SitioCurso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SitioCursoDao extends Generico<SitioCurso> {

    @PersistenceContext
    private EntityManager em;

    public SitioCursoDao() {
        super(SitioCurso.class);
    }
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar los sitios donde se realizaran los cursos
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<SitioCurso> listarSitios() {
        String sql = "SELECT s FROM SitioCurso s WHERE s.estado.idEstado = 1 ORDER BY s.idSitioCurso";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
