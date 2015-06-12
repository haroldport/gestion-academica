package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.InstructorDao;
import gestion.academica.modelo.Instructor;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class InstructorServicio {
	
	@EJB
	private InstructorDao instructorDao;
	
	/**
	 * Crear un nuevo instructor
	 * @param instructor
	 */
	public void crear(Instructor instructor){
		try{
			instructorDao.create(instructor);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar un instructor existente
	 * @param instructor
	 */
	public void editar(Instructor instructor){
		try{
			instructorDao.edit(instructor);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Listar todos los instructores activos
     * @return
     */
	public List<Instructor> listarInstructores() {
    	return instructorDao.listarInstructores();
    }

}
