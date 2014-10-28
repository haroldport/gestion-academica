package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.CatalogoDao;
import gestion.academica.modelo.Catalogo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CatalogoServicio {
	
	@EJB
	private CatalogoDao catalogoDao;
	
	/**
	 * Crear un nuevo catalogo
	 * @param catalogo
	 */
	public void crear(Catalogo catalogo){
		try{
			catalogoDao.create(catalogo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un catalogo existente
	 * @param catalogo
	 */
	public void editar(Catalogo catalogo){
		try{
			catalogoDao.edit(catalogo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Listar todos los catalogos
     * @return
     */
	public List<Catalogo> listarCatalogos() {
		return catalogoDao.listarCatalogos();
	}
	
	/**
     * Buscar catalogo en base al nemonico
     * @param nemonico
     * @return
     */
    public Catalogo obtenerPorNemonico(String nemonico) {
    	return catalogoDao.obtenerPorNemonico(nemonico);
    }

}
