package gestion.academica.dao;

import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Matricula;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    /**
     * Listar las matriculas
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Matricula> listarMatriculas() {
        String sql = "SELECT m FROM Matricula m WHERE m.estado.idEstado = 1 ORDER BY m.idMatricula";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
    
    /**
     * Buscar por estudiante y curso
     * @param estudiante
     * @param curso
     * @return
     */
    public Matricula buscarPorEstudianteYCurso(Estudiante estudiante, Curso curso) {
    	try {
            Query query = this.em.createQuery("SELECT m FROM Matricula m WHERE m.estado.idEstado = 1 AND m.curso = :curso AND m.estudiante = :estudiante");
            query.setParameter("estudiante", estudiante);
            query.setParameter("curso", curso);
            return query.getResultList() != null && !query.getResultList().isEmpty() ? (Matricula) query.getResultList().get(0) : null;
        } catch (Exception e) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
