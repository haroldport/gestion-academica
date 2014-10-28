package gestion.academica.dao;

import gestion.academica.modelo.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
