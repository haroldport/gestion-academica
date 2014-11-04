package gestion.academica.dao;

import java.util.List;

import gestion.academica.modelo.InformacionCurso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InformacionCursoDao extends Generico<InformacionCurso> {

    @PersistenceContext
    private EntityManager em;

    public InformacionCursoDao() {
        super(InformacionCurso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar todas las informaciones de curso
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<InformacionCurso> listarInformacionDeCursos() {
        String sql = "SELECT i FROM InformacionCurso i WHERE i.estado.idEstado = 1 ORDER BY i.idInformacionCurso";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
