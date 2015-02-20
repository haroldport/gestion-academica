package gestion.academica.servicio;

import gestion.academica.dao.PreinscripcionDao;
import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Preinscripcion;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PreinscripcionServicio {
	
	@EJB
	private PreinscripcionDao preinscripcionDao;
	
	/**
	 * Crear una preinscripcion
	 * @param preinscripcion
	 */
	public void crear(Preinscripcion preinscripcion){
		try {
			preinscripcionDao.create(preinscripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Obtener por cliente y curso
     * @param cliente
     * @param curso
     * @return
     */
    public Preinscripcion obtenerPorClienteYCurso(Cliente cliente, Curso curso) {
    	return preinscripcionDao.obtenerPorClienteYCurso(cliente, curso);
    }

}
