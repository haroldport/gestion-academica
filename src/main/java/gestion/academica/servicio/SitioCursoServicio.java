package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.SitioCursoDao;
import gestion.academica.modelo.SitioCurso;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SitioCursoServicio {
	
	@EJB
	private SitioCursoDao sitioCursoDao;
	
	/**
	 * Crear un nuevo sitio
	 * @param sitioCurso
	 */
	public void crear(SitioCurso sitioCurso){
		try {
			sitioCursoDao.create(sitioCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un sitio existente
	 * @param sitioCurso
	 */
	public void editar(SitioCurso sitioCurso){
		try {
			sitioCursoDao.edit(sitioCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Listar los sitios donde se realizaran los cursos
     * @return
     */
	public List<SitioCurso> listarSitios() {
    	return sitioCursoDao.listarSitios();
    }

}
