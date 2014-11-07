package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.TemaCursoDao;
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
	public List<TemaCurso> listarTemasCurso() {
    	return temaCursoDao.listarTemasCurso();
    }

}
