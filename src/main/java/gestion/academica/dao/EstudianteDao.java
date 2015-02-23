package gestion.academica.dao;

import java.util.List;

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
    
    /**
     * Listar los estudiantes
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Estudiante> listarEstudiantes() {
        String sql = "SELECT e FROM Estudiante e WHERE e.estado.idEstado = 1 ORDER BY e.idEstudiante";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
