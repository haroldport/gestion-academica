package gestion.academica.servicio;

import java.util.List;

import gestion.academica.dao.MatriculaDao;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Matricula;
import gestion.academica.modelo.Rol;
import gestion.academica.modelo.Usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MatriculaServicio {
	
	@EJB
	private MatriculaDao matriculaDao;
	@EJB
	private EstudianteServicio estudianteServicio;
	@EJB
	private UsuarioServicio usuarioServicio;
	
	/**
	 * Crear matricula
	 * @param matricula
	 */
	public void crear(Matricula matricula){
		try {
			matriculaDao.create(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar matricula
	 * @param matricula
	 */
	public void editar(Matricula matricula){
		try {
			matriculaDao.edit(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Crear estudiante y matricula
	 * @param estudiante
	 * @param matricula
	 */
	public void crearEstudianteYMatricula(Estudiante estudiante, Matricula matricula, Rol rolMatriculado){		
		try {
			Usuario usuario = estudiante.getUsuario();
			usuario.setRol(rolMatriculado);
			if(usuario.getIdUsuario() == null){
				usuarioServicio.ingresar(usuario);
				estudiante.setUsuario(usuario);
			}else{
				usuarioServicio.actualizar(usuario);
			}
			if(estudiante.getIdEstudiante() == null){
				estudianteServicio.crear(estudiante);
			}			
			matricula.setEstudiante(estudiante);
			matriculaDao.create(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Editar estudiante y matricula
	 * @param estudiante
	 * @param matricula
	 */
	public void editarEstudianteYMatricula(Estudiante estudiante, Matricula matricula){		
		try {
			estudianteServicio.editar(estudiante);
			matricula.setEstudiante(estudiante);
			matriculaDao.edit(matricula);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Listar las matriculas
     * @return
     */
	public List<Matricula> listarMatriculas() {
    	return matriculaDao.listarMatriculas();
    }
	
	/**
     * Buscar por estudiante y curso
     * @param estudiante
     * @param curso
     * @return
     */
    public Matricula buscarPorEstudianteYCurso(Estudiante estudiante, Curso curso) {
    	return matriculaDao.buscarPorEstudianteYCurso(estudiante, curso);
    }

}
