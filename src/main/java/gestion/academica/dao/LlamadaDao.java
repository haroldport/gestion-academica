package gestion.academica.dao;

import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Llamada;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LlamadaDao extends Generico<Llamada> {

    @PersistenceContext
    private EntityManager em;

    public LlamadaDao() {
        super(Llamada.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar llamadas por cliente y curso
     * @param cliente
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Llamada> listarLlamadasPorClienteYCurso(Cliente cliente, Curso curso) {
        String sql = "SELECT l FROM Llamada l WHERE l.estado.idEstado = 1 AND l.cliente = :cliente AND l.curso = :curso ORDER BY l.idLlamada";
        return this.getEntityManager().createQuery(sql).setParameter("cliente", cliente).setParameter("curso", curso).getResultList();
    }

}
