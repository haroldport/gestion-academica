package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.EstudianteDao;
import gestion.academica.modelo.Estudiante;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EstudianteServicio {
	
	@EJB
	private EstudianteDao estudianteDao;
	
	/**
	 * Crear estudiante
	 * @param estudiante
	 */
	public void crear(Estudiante estudiante){
		try {
			estudianteDao.create(estudiante);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar estudiante
	 * @param estudiante
	 */
	public void editar(Estudiante estudiante){
		try {
			estudianteDao.edit(estudiante);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Listar los estudiantes
     * @return
     */
	public List<Estudiante> listarEstudiantes() {
    	return estudianteDao.listarEstudiantes();
    }
	
	/**
     * Buscar estudiante en base al numero de documento
     * @param numeroDocumento
     * @return
     */
    public Estudiante buscarPorNumeroDocumento(String numeroDocumento) {
    	return estudianteDao.buscarPorNumeroDocumento(numeroDocumento);
    }

}
