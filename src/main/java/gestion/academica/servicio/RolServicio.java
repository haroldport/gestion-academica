package gestion.academica.servicio;

import gestion.academica.dao.RolDao;
import gestion.academica.modelo.Rol;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Servicio que contiene los servicios de la entidad Rol.
 *
 * @author Harold Portocarrero
 * @version $Revision: 1.0 $
 *
 */
@LocalBean
@Stateless
public class RolServicio {

    @EJB
    private RolDao rolDao;

    /**
     * Servicio que ingresa un Rol.
     *
     * @param rol
     */
    public void ingresar(Rol rol) throws Exception {
        rolDao.create(rol);
    }
    
    /**
     * Servicio que permite actualizar un Rol.
     *
     * @param rol
     */
    public void actualizar(Rol rol) throws Exception {
        rolDao.edit(rol);
    }
    
    /**
     * Servicio que permite eliminar un Rol.
     *
     * @param rol
     */
    public void eliminar(Rol rol) throws Exception {
        rolDao.remove(rol);
    }
    
    /**
     * Servicio que lista todas l0s Catalogos.
     *
     * @param List<Rol>
     */
    public List<Rol> listarRoles() {
        return rolDao.listarRoles();
    }
   
    /**
     * Servicio que retorna un Catalogo dado un nemonico.
     *
     * @param nemonico
     *
     * @param Catalogo
     */
    public Rol obtenerPorNemonico(String nemonico) {
        return rolDao.obtenerPorNemonico(nemonico);
    }
}
