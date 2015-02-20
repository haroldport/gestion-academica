package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.TemaCursoDao;
import gestion.academica.modelo.InformacionCurso;
import gestion.academica.modelo.TemaCurso;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TemaCursoServicio {
	
	@EJB
	private TemaCursoDao temaCursoDao;
	
	/**
	 * Crear un nuevo tema para curso
	 * @param temaCurso
	 */
	public void crear(TemaCurso temaCurso){
		try{
			temaCursoDao.create(temaCurso);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un tema existente
	 * @param temaCurso
	 */
	public void editar(TemaCurso temaCurso){
		try{
			temaCursoDao.edit(temaCurso);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Listar todos los temas
     * @return
     */
	public List<TemaCurso> listarTemasCursoPadres() {
    	return temaCursoDao.listarTemasCursoPadres();
    }
	
	/**
     * Listar temas padres en base a un curso
     * @param informacionCurso
     * @return
     */
	public List<TemaCurso> listarTemasPadresPorCurso(InformacionCurso informacionCurso) {
    	return temaCursoDao.listarTemasPadresPorCurso(informacionCurso);
    }

}
