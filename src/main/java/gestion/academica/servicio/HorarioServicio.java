package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.HorarioDao;
import gestion.academica.modelo.Horario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HorarioServicio {
	
	@EJB
	private HorarioDao horarioDao;
	
	/**
	 * Crear nuevo horario
	 * @param horario
	 */
	public void crear(Horario horario){
		try{
			horarioDao.create(horario);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar horario existente
	 * @param horario
	 */
	public void editar(Horario horario){
		try{
			horarioDao.edit(horario);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Listar horarios
     * @return
     */
	public List<Horario> listarHorarios() {
    	return horarioDao.listarHorarios();
    }

}
