package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.CursoDao;
import gestion.academica.modelo.Curso;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CursoServicio {
	
	@EJB
	private CursoDao cursoDao;
	
	/**
	 * Crear un nuevo curso
	 * @param curso
	 */
	public void crear(Curso curso){
		try {
			cursoDao.create(curso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un curso existente
	 * @param curso
	 */
	public void editar(Curso curso){
		try {
			cursoDao.edit(curso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Listar todos los cursos activos
     * @return
     */
	public List<Curso> listarCursos() {
    	return cursoDao.listarCursos();
    }
	
	/**
     * Buscar por id
     * @param idCurso
     * @return
     */
    public Curso buscarPorId(Integer idCurso) {
    	return cursoDao.buscarPorId(idCurso);
    }

}
