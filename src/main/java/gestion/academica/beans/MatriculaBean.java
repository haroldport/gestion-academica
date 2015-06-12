package gestion.academica.beans;

import gestion.academica.dto.EstadisticaDTO;
import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Curso;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Matricula;
import gestion.academica.modelo.Rol;
import gestion.academica.modelo.Usuario;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.CursoServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.EstudianteServicio;
import gestion.academica.servicio.MatriculaServicio;
import gestion.academica.servicio.RolServicio;
import gestion.academica.utilitario.Crypt;
import gestion.academica.utilitario.Utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class MatriculaBean extends Utilitario implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private MatriculaServicio MatriculaServicio;
	@EJB
	private MatriculaServicio matriculaServicio;
	@EJB
	private RolServicio rolServicio;
	@EJB
	private CursoServicio cursoServicio;
	@EJB
	private EstudianteServicio estudianteServicio;
	
	private Matricula nuevaMatricula;
	private Matricula eliminarMatricula;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(MatriculaBean.class.getName());
	private static final String DOBLE_BARRA = "\\";
	private Bitacora bitacora;
	private boolean editarMatricula;
	private Rol rolMatriculado;
	private List<Matricula> listaMatriculas;
	private List<Curso> listaCursos;
	
	//Para carga masiva
	private File adjunto;
	private List<String> listaArchivo;
	private String destino = getRequest().getSession().getServletContext()
			.getRealPath("/upload/") + DOBLE_BARRA;
	private int contadorRegistros;
	private int numeroTotalRegistros;
	private int contadorRegistrosVacios;
	private int contadorRegistrosRechazados;
	private int contadorRegistrosProcesados;
	private boolean mostrarResultados;
	private boolean mostrarDetalleErrores;
	private List<EstadisticaDTO> listaEstadisticas;
	private List<EstadisticaDTO> listaEstadisticasFilter;
	private EstadisticaDTO registroEstadistica;
	private Estudiante estudianteMasivo;
	private Curso cursoMasivo;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarMatricula = new Matricula();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			rolMatriculado = rolServicio.obtenerPorNemonico("RMAT");
			listaCursos = cursoServicio.listarCursos();
			initValores();
			listaArchivo = new ArrayList<>();
			inicializarValoresCargaMasiva();
			this.setEditarMatricula(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevaMatricula = new Matricula();
		nuevaMatricula.setCurso(new Curso());
		nuevaMatricula.setEstudiante(new Estudiante());
		nuevaMatricula.getEstudiante().setUsuario(new Usuario());
		listaMatriculas = matriculaServicio.listarMatriculas();
	}
	
	public void verificarEstudianteExiste(){
		Estudiante estudiante = estudianteServicio.buscarPorNumeroDocumento(nuevaMatricula.getEstudiante().getNumeroDocumento());
		if(estudiante != null){
			nuevaMatricula.setEstudiante(estudiante);
		}
	}
	
	public String seleccionarMatricula(Matricula Matricula) {
		setEliminarMatricula(Matricula);
		return "";
	}

	public String editarMatricula() {
		try {
			Date fechaActualizacion = new Date();
			setEditarMatricula(false);
			nuevaMatricula.getEstudiante().setNombres(nuevaMatricula.getEstudiante().getNombres().toUpperCase());			
			matriculaServicio.editarEstudianteYMatricula(nuevaMatricula.getEstudiante(), nuevaMatricula);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de Matricula: " + nuevaMatricula.getIdMatricula(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("La Matricula fue actualizada correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarMatricula(Matricula matricula) {
		setEditarMatricula(true);
		setNuevaMatricula(matricula);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			Curso curso = cursoServicio.buscarPorId(nuevaMatricula.getCurso().getIdCurso());
			nuevaMatricula.setCurso(curso);
			if(curso.getCupoDisponible() > 0){
				Matricula matricula = matriculaServicio.buscarPorEstudianteYCurso(nuevaMatricula.getEstudiante(), nuevaMatricula.getCurso());
				if(matricula == null){
					nuevaMatricula.setEstado(estadoActivo);
					nuevaMatricula.setFechaMatricula(fechaCreacion);
					nuevaMatricula.getEstudiante().setNombres(nuevaMatricula.getEstudiante().getNombres().toUpperCase());
					if(nuevaMatricula.getEstudiante().getUsuario().getUsername() == null){
						nuevaMatricula.getEstudiante().getUsuario().setUsername(nuevaMatricula.getEstudiante().getNumeroDocumento());
						nuevaMatricula.getEstudiante().getUsuario().setClave(Crypt.encryptMD5(nuevaMatricula.getEstudiante().getNumeroDocumento()));
						nuevaMatricula.getEstudiante().getUsuario().setEstado(estadoActivo);
					}
					matriculaServicio.crearEstudianteYMatricula(nuevaMatricula.getEstudiante(), nuevaMatricula, rolMatriculado);
					curso.setCupoDisponible(curso.getCupoDisponible() - 1);
					cursoServicio.editar(curso);
					bitacora = new Bitacora(fechaCreacion, "Creación de Matricula: " + nuevaMatricula.getIdMatricula(), this.getUsuario());
		            bitacoraServicio.crear(bitacora);
					this.ponerMensajeInfo("La Matricula fue creada correctamente", "");
					initValores();
				}else{
					ponerMensajeError("Error - El(la) estudiante: " + nuevaMatricula.getEstudiante().getNombres() + 
							", ya está matriculad@ en el curso: " + nuevaMatricula.getCurso().getInformacionCurso().getNombre(), "");
				}
			}else{
				ponerMensajeError("Ya no hay cupos disponibles en este Curso", "");
			}
						
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarMatricula.setEstado(estadoInactivo);
			matriculaServicio.editar(eliminarMatricula);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de Matricula: " + eliminarMatricula.getIdMatricula(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarMatricula = new Matricula();
			this.ponerMensajeInfo("La Matricula fue eliminada correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	//carga masiva
	private void inicializarValoresCargaMasiva() {
		setContadorRegistrosRechazados(0);
		setContadorRegistrosProcesados(0);
		setContadorRegistros(0);
		listaEstadisticas = new ArrayList<>();
		this.setMostrarDetalleErrores(Boolean.FALSE);
		this.setMostrarResultados(Boolean.FALSE);
	}
	
	
	public void upload(FileUploadEvent evt) throws Exception {
		this.adjunto = null;
		inicializarValoresCargaMasiva();
		UploadedFile file1 = evt.getFile();
		this.adjunto = new File(destino + getRequest().getSession().getId()
				+ file1.getFileName());
		FileOutputStream out = new FileOutputStream(this.getAdjunto());
		out.write(file1.getContents());
		listarLineasArchivo();
		out.close();
	}
	
	public void listarLineasArchivo() throws Exception {
		setNumeroTotalRegistros(0);
		setContadorRegistrosVacios(0);
		this.listaArchivo = new ArrayList<String>();
		FileReader fileReader = new FileReader(this.adjunto);
		BufferedReader br = new BufferedReader(fileReader);
		String linea = "";
		while ((linea = br.readLine()) != null) {
			String s = linea.trim();
			if (s != null) {
				if (obtenerTamanioString(s) == 0) {
					contadorRegistrosVacios++;
				} else {
					this.listaArchivo.add(s);
				}
			} else {
				contadorRegistrosVacios++;
			}
			if (contadorRegistrosVacios == 3) {
				break;
			}
		}
		setNumeroTotalRegistros(listaArchivo.size() - 1);
		fileReader.close();
		br.close();
	}
	
	public void matriculaMasiva() throws NumberFormatException, Exception{
		if (contadorRegistrosVacios == 3 && numeroTotalRegistros == 0) {
			ponerMensajeError("El archivo contiene 3 o más registros vacíos, revise si se encuentra correctamente estructurado.","");
		} else {
			int cont = 0;
			inicializarValoresCargaMasiva();
			for (String registro : listaArchivo) {
				if (cont > 0) {
					contadorRegistros++;
					estudianteMasivo = new Estudiante();
					cursoMasivo = new Curso();
					if (validaRegistro(registro.trim(), contadorRegistros)) {
						realizarMatricula();
					}
				}
				cont++;
			}
		}
		this.setMostrarResultados(Boolean.TRUE);
		initValores();
	}
	
	
	private boolean validaRegistro(String registro, int numeroRegistro){
		boolean result = true;
		if(!validaCurso(Integer.parseInt(registro.split(";")[0].trim()), numeroRegistro) || 
				!validaEstudiante(registro.split(";")[1].trim(), registro.split(";")[2].trim(), registro.split(";")[3].trim(), numeroRegistro)
				|| !validaMatricula(numeroRegistro)){
			contadorRegistrosRechazados++;
			result = false;
		}
		return result;
	}
	
	private boolean validaCurso(Integer idCurso, int numeroRegistro){
		boolean result = true;
		cursoMasivo = cursoServicio.buscarPorId(idCurso);
		if(cursoMasivo == null){
			result = false;
			registrarEstadistica(numeroRegistro, "Curso", "El curso no existe");
		}else if(cursoMasivo.getCupoDisponible() == 0){
			result = false;
			registrarEstadistica(numeroRegistro, "Curso", "El curso ya no tiene cupos disponible, no se pudo matricular al estudiante");
		}
		return result;
	}
	
	private boolean validaEstudiante(String numeroDocumento, String nombres, String email, int numeroRegistro){
		boolean result = true;
		try{
			estudianteMasivo = estudianteServicio.buscarPorNumeroDocumento(numeroDocumento);
			if(estudianteMasivo == null){
				Usuario usuario = new Usuario();
				usuario.setUsername(numeroDocumento);
				usuario.setClave(Crypt.encryptMD5(numeroDocumento));
				usuario.setEstado(estadoActivo);
				estudianteMasivo = new Estudiante(email, nombres, numeroDocumento, estadoActivo, usuario);
			}
		}catch(Exception e){
			result = false;
			registrarEstadistica(numeroRegistro, "Estudiante", "Ocurrió un error al recuperar o al crear al estudiante");
		}		
		return result;
	}
	
	private boolean validaMatricula(int numeroRegistro){
		boolean result = true;
		if(cursoMasivo != null && estudianteMasivo != null){
			try{
				if(estudianteMasivo.getIdEstudiante() != null){
					Matricula matricula = matriculaServicio.buscarPorEstudianteYCurso(estudianteMasivo, cursoMasivo);
					if(matricula != null){
						result = false;
						registrarEstadistica(numeroRegistro, "Matricula", "El estudiante ya está registrado en este curso");
					}
				}				
			}catch(Exception e){
				LOGGER.log(Level.SEVERE, null, e);
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}
	
	private void registrarEstadistica(int numeroRegistro, String nombreCampo, String descripcionProblema){
		registroEstadistica = new EstadisticaDTO(
				numeroRegistro,nombreCampo,descripcionProblema);
		listaEstadisticas.add(registroEstadistica);
	}
	
	private void realizarMatricula(){
		Date fechaMatricula = new Date();
		if(cursoMasivo != null && estudianteMasivo != null){
			Matricula matricula = new Matricula(fechaMatricula, cursoMasivo, estadoActivo);
			matriculaServicio.crearEstudianteYMatricula(estudianteMasivo, matricula, rolMatriculado);
			cursoMasivo.setCupoDisponible(cursoMasivo.getCupoDisponible() - 1);
			cursoServicio.editar(cursoMasivo);
			bitacora = new Bitacora(fechaMatricula, "Creación de Matricula (proceso masivo): " + matricula.getIdMatricula(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
            contadorRegistrosProcesados++;
		}
	}
	
	public void mostrarErrores() {
		this.setMostrarDetalleErrores(Boolean.TRUE);
	}
	
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);
			cell.setCellStyle(cellStyle);
		}
	}
	
	public Matricula getNuevaMatricula() {
		return nuevaMatricula;
	}

	public void setNuevaMatricula(Matricula nuevaMatricula) {
		this.nuevaMatricula = nuevaMatricula;
	}

	public Matricula getEliminarMatricula() {
		return eliminarMatricula;
	}

	public void setEliminarMatricula(Matricula eliminarMatricula) {
		this.eliminarMatricula = eliminarMatricula;
	}

	public boolean isEditarMatricula() {
		return editarMatricula;
	}

	public void setEditarMatricula(boolean editarMatricula) {
		this.editarMatricula = editarMatricula;
	}

	public Estado getEstadoActivo() {
		return estadoActivo;
	}
	
	public void setEstadoActivo(Estado estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	
	public Estado getEstadoInactivo() {
		return estadoInactivo;
	}
	
	public void setEstadoInactivo(Estado estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
	}
	
	public Bitacora getBitacora() {
		return bitacora;
	}
	
	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}

	public Rol getRolMatriculado() {
		return rolMatriculado;
	}

	public void setRolMatriculado(Rol rolMatriculado) {
		this.rolMatriculado = rolMatriculado;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public File getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(File adjunto) {
		this.adjunto = adjunto;
	}

	public List<String> getListaArchivo() {
		return listaArchivo;
	}

	public void setListaArchivo(List<String> listaArchivo) {
		this.listaArchivo = listaArchivo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getContadorRegistros() {
		return contadorRegistros;
	}

	public void setContadorRegistros(int contadorRegistros) {
		this.contadorRegistros = contadorRegistros;
	}

	public int getNumeroTotalRegistros() {
		return numeroTotalRegistros;
	}

	public void setNumeroTotalRegistros(int numeroTotalRegistros) {
		this.numeroTotalRegistros = numeroTotalRegistros;
	}

	public int getContadorRegistrosVacios() {
		return contadorRegistrosVacios;
	}

	public void setContadorRegistrosVacios(int contadorRegistrosVacios) {
		this.contadorRegistrosVacios = contadorRegistrosVacios;
	}

	public int getContadorRegistrosRechazados() {
		return contadorRegistrosRechazados;
	}

	public void setContadorRegistrosRechazados(int contadorRegistrosRechazados) {
		this.contadorRegistrosRechazados = contadorRegistrosRechazados;
	}

	public int getContadorRegistrosProcesados() {
		return contadorRegistrosProcesados;
	}

	public void setContadorRegistrosProcesados(int contadorRegistrosProcesados) {
		this.contadorRegistrosProcesados = contadorRegistrosProcesados;
	}

	public boolean isMostrarResultados() {
		return mostrarResultados;
	}

	public void setMostrarResultados(boolean mostrarResultados) {
		this.mostrarResultados = mostrarResultados;
	}

	public boolean isMostrarDetalleErrores() {
		return mostrarDetalleErrores;
	}

	public void setMostrarDetalleErrores(boolean mostrarDetalleErrores) {
		this.mostrarDetalleErrores = mostrarDetalleErrores;
	}

	public List<EstadisticaDTO> getListaEstadisticas() {
		return listaEstadisticas;
	}

	public void setListaEstadisticas(List<EstadisticaDTO> listaEstadisticas) {
		this.listaEstadisticas = listaEstadisticas;
	}

	public EstadisticaDTO getRegistroEstadistica() {
		return registroEstadistica;
	}

	public void setRegistroEstadistica(EstadisticaDTO registroEstadistica) {
		this.registroEstadistica = registroEstadistica;
	}

	public Estudiante getEstudianteMasivo() {
		return estudianteMasivo;
	}

	public void setEstudianteMasivo(Estudiante estudianteMasivo) {
		this.estudianteMasivo = estudianteMasivo;
	}

	public Curso getCursoMasivo() {
		return cursoMasivo;
	}

	public void setCursoMasivo(Curso cursoMasivo) {
		this.cursoMasivo = cursoMasivo;
	}

	public List<EstadisticaDTO> getListaEstadisticasFilter() {
		return listaEstadisticasFilter;
	}

	public void setListaEstadisticasFilter(
			List<EstadisticaDTO> listaEstadisticasFilter) {
		this.listaEstadisticasFilter = listaEstadisticasFilter;
	}

}
