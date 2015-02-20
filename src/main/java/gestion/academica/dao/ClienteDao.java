package gestion.academica.dao;

import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClienteDao extends Generico<Cliente> {

    @PersistenceContext
    private EntityManager em;

    public ClienteDao() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtener por usuario
     * @param usuario
     * @return
     */
    public Cliente obtenerPorUsuario(Usuario usuario) {
        try {
            String sql = "SELECT c FROM Cliente c WHERE c.estado.idEstado = 1 "
            		+ "AND c.usuario = :usuario ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("usuario", usuario);
            Cliente cliente = query.getResultList() != null && !query.getResultList().isEmpty() ? (Cliente) query.getResultList().get(0) : null;
            return cliente;
        } catch (Exception e) {
            Logger.getLogger(PreinscripcionDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
