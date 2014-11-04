package gestion.academica.servicio;
import gestion.academica.modelo.Imagen;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Rivera
 */
@LocalBean
@Stateless
public class FileServicio {
    @PersistenceContext
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void ingresarFile(Imagen file){
        getEntityManager().persist(file);
    }
    
    public void eliminarFile(Imagen file){
    	getEntityManager().remove(file);
    }
    
    public Imagen obtenerFile(Integer id){
        return (Imagen)getEntityManager().createQuery("SELECT i FROM Imagen i where i.idImagen = :id").setParameter("id", id).getSingleResult();
    }
}
