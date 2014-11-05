package gestion.academica.dao;

import gestion.academica.modelo.Instructor;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InstructorDao extends Generico<Instructor> {

    @PersistenceContext
    private EntityManager em;

    public InstructorDao() {
        super(Instructor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar todos los instructores activos
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Instructor> listarInstructores() {
        String sql = "SELECT i FROM Instructor i WHERE i.estado.idEstado = 1 ORDER BY i.idInstructor";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
