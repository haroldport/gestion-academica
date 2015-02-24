package gestion.academica.dao;

import java.util.List;

import gestion.academica.modelo.Curso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CursoDao extends Generico<Curso> {

    @PersistenceContext
    private EntityManager em;

    public CursoDao() {
        super(Curso.class);
    }
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar todos los cursos activos
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Curso> listarCursos() {
        String sql = "SELECT c FROM Curso c WHERE c.estado.idEstado = 1 ORDER BY c.idCurso";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
    
    /**
     * Buscar por id
     * @param idCurso
     * @return
     */
    public Curso buscarPorId(Integer idCurso) {
        String sql = "SELECT c FROM Curso c WHERE c.estado.idEstado = 1 AND c.idCurso = :idCurso";
        return (Curso) this.getEntityManager().createQuery(sql).setParameter("idCurso", idCurso).getResultList().get(0);
    }

}
