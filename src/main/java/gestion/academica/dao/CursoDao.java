package gestion.academica.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gestion.academica.modelo.Curso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    	try {
            Query query = this.em.createQuery("SELECT c FROM Curso c WHERE c.estado.idEstado = 1 AND c.idCurso = :idCurso");
            query.setParameter("idCurso", idCurso);
            return query.getResultList() != null && !query.getResultList().isEmpty() ? (Curso) query.getResultList().get(0) : null;
        } catch (Exception e) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
