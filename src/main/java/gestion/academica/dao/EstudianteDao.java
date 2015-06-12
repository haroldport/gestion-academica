package gestion.academica.dao;

import gestion.academica.modelo.Estudiante;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    /**
     * Listar los estudiantes
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Estudiante> listarEstudiantes() {
        String sql = "SELECT e FROM Estudiante e WHERE e.estado.idEstado = 1 ORDER BY e.idEstudiante";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
    
    /**
     * Buscar estudiante en base al numero de documento
     * @param numeroDocumento
     * @return
     */
    public Estudiante buscarPorNumeroDocumento(String numeroDocumento) {
    	try {
            Query query = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.estado.idEstado = 1 AND e.numeroDocumento = :numeroDocumento");
            query.setParameter("numeroDocumento", numeroDocumento);
            return query.getResultList() != null && !query.getResultList().isEmpty() ? (Estudiante) query.getResultList().get(0) : null;
        } catch (Exception e) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
