package gestion.academica.dao;

import gestion.academica.modelo.Curso;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Listar todos los cursos activos y que no hayan caducado
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Curso> listarCursos() {
    	Date fechaActual = new Date();
        String sql = "SELECT c FROM Curso c WHERE c.estado.idEstado = 1 AND c.fechaFin > :fechaActual ORDER BY c.idCurso";
        return this.getEntityManager().createQuery(sql).setParameter("fechaActual", fechaActual).getResultList();
    }
    
    /**
     * Listar todos los cursos activos
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Curso> listarTodos() {
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
