package gestion.academica.dao;

import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Preinscripcion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PreinscripcionDao extends Generico<Preinscripcion> {

    @PersistenceContext
    private EntityManager em;

    public PreinscripcionDao() {
        super(Preinscripcion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtener por cliente y curso
     * @param cliente
     * @param curso
     * @return
     */
    public Preinscripcion obtenerPorClienteYCurso(Cliente cliente, Curso curso) {
        try {
            String sql = "SELECT p FROM Preinscripcion p WHERE p.estado.idEstado = 1 "
            		+ "AND p.cliente = :cliente AND p.curso = :curso ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("cliente", cliente);
            query.setParameter("curso", curso);
            Preinscripcion preinscripcion = query.getResultList() != null && !query.getResultList().isEmpty() ? (Preinscripcion) query.getResultList().get(0) : null;
            return preinscripcion;
        } catch (Exception e) {
            Logger.getLogger(PreinscripcionDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    /**
     * Listar preinscripciones
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Preinscripcion> listarPreinscripciones() {
        String sql = "SELECT p FROM Preinscripcion p WHERE p.estado.idEstado = 1 ORDER BY p.idPreinscripcion DESC";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
