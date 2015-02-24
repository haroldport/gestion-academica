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
    
    /**
     * Buscar estudiante en base al numero de documento
     * @param numeroDocumento
     * @return
     */
    public Estudiante buscarPorNumeroDocumento(String numeroDocumento) {
        String sql = "SELECT e FROM Estudiante e WHERE e.estado.idEstado = 1 AND e.numeroDocumento = :numeroDocumento ";
        return (Estudiante) this.getEntityManager().createQuery(sql).setParameter("numeroDocumento", numeroDocumento).getResultList().get(0);
    }

}
