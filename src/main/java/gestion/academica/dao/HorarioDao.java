package gestion.academica.dao;

import java.util.List;

import gestion.academica.modelo.Horario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HorarioDao extends Generico<Horario> {

    @PersistenceContext
    private EntityManager em;

    public HorarioDao() {
        super(Horario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Listar horarios
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Horario> listarHorarios() {
        String sql = "SELECT h FROM Horario h WHERE h.estado.idEstado = 1 ORDER BY h.idHorario";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

}
