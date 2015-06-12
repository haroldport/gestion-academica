package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.LlamadaDao;
import gestion.academica.modelo.Cliente;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Llamada;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LlamadaServicio {
	
	@EJB
	private LlamadaDao llamadaDao;
	
	/**
	 * Crear una nueva llamada
	 * @param llamada
	 */
	public void crear(Llamada llamada){
		try {
			llamadaDao.create(llamada);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Listar llamadas por cliente y curso
     * @param cliente
     * @param curso
     * @return
     */
	public List<Llamada> listarLlamadasPorClienteYCurso(Cliente cliente, Curso curso) {
    	return llamadaDao.listarLlamadasPorClienteYCurso(cliente, curso);
    }

}
