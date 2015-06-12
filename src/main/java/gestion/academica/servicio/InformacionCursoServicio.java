package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.InformacionCursoDao;
import gestion.academica.modelo.InformacionCurso;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class InformacionCursoServicio {
	
	@EJB
	private InformacionCursoDao informacionCursoDao;
	
	/**
	 * Crear una nueva informacion
	 * @param infoCurso
	 */
	public void crear(InformacionCurso infoCurso){
		try{
			informacionCursoDao.create(infoCurso);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar informacion existente
	 * @param infoCurso
	 */
	public void editar(InformacionCurso infoCurso){
		try{
			informacionCursoDao.edit(infoCurso);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Listar todas las informaciones de curso
     * @return
     */
	public List<InformacionCurso> listarInformacionDeCursos() {
    	return informacionCursoDao.listarInformacionDeCursos();
    }

}
