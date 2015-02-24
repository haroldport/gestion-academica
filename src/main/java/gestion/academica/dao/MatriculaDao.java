package gestion.academica.dao;

import java.util.List;

import gestion.academica.modelo.Matricula;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
