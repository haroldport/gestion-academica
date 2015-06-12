package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Imagen;
import gestion.academica.modelo.InformacionCurso;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.FileServicio;
import gestion.academica.servicio.InformacionCursoServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
@ViewScoped
public class InfoCursosBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String pathDestino = getRequest().getSession().getServletContext().getRealPath("/resources/images/") + "//"; 
	
	@EJB
    private FileServicio fileServicio;
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private InformacionCursoServicio informacionCursoServicio;
	
	private InformacionCurso nuevaInfoCurso;
	private InformacionCurso eliminarInfoCurso;
	private List<InformacionCurso> listadoInfoCurso;
	private static final Logger LOGGER = Logger.getLogger(InfoCursosBean.class.getName());
	private static final int BUFFER_SIZE = 200000;
	private Imagen imagen;
	private File result;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private boolean editarInfoCurso;
	private Bitacora bitacora;
	
	@PostConstruct
	public void iniciar() {
		try {
			eliminarInfoCurso = new InformacionCurso();
			initValores();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			this.setEditarInfoCurso(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void eliminarImagen(Imagen imagenEliminar){
		if(imagenEliminar != null){
			fileServicio.eliminarFile(imagenEliminar);
		}
	}
	
	private void initValores(){
		nuevaInfoCurso = new InformacionCurso();
		nuevaInfoCurso.setImagen(new Imagen());
		listadoInfoCurso = informacionCursoServicio.listarInformacionDeCursos();
	}
	
	public String seleccionarInfoCurso(InformacionCurso infoCurso) {
		setEliminarInfoCurso(infoCurso);
		return "";
	}

	public String editarInfoCurso() {
		try {
			Date fechaActualizacion = new Date();
			setEditarInfoCurso(false);
			nuevaInfoCurso.setNombre(nuevaInfoCurso.getNombre().toUpperCase());
			nuevaInfoCurso.setObjetivo(nuevaInfoCurso.getObjetivo().toUpperCase());
			informacionCursoServicio.editar(nuevaInfoCurso);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de información de curso: " + nuevaInfoCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("La información del curso fue actualizada correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarInfoCurso(InformacionCurso infoCurso) {
		setEditarInfoCurso(true);
		setNuevaInfoCurso(infoCurso);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			nuevaInfoCurso.setEstado(estadoActivo);
			nuevaInfoCurso.setNombre(nuevaInfoCurso.getNombre().toUpperCase());
			nuevaInfoCurso.setObjetivo(nuevaInfoCurso.getObjetivo().toUpperCase());
			informacionCursoServicio.crear(nuevaInfoCurso);
			bitacora = new Bitacora(fechaCreacion, "Creación de información de curso: " + nuevaInfoCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("La información del curso fue creada correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarInfoCurso.setEstado(estadoInactivo);
			eliminarImagen(eliminarInfoCurso.getImagen());
			informacionCursoServicio.editar(eliminarInfoCurso);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de información del curso: " + eliminarInfoCurso.getNombre(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarInfoCurso = new InformacionCurso(); 
			this.ponerMensajeInfo("La información del curso fue eliminada correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	@SuppressWarnings("resource")
	public void cargarImagen(FileUploadEvent event) {
		setImagen(new Imagen());
        getImagen().setTipoArchivo(event.getFile().getContentType());
        getImagen().setExtension(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")));
        getImagen().setNombre(StringClean(event.getFile().getFileName().substring(0, event.getFile().getFileName().lastIndexOf("."))));
        result = new File(pathDestino + event.getFile().getFileName());
        InputStream inputStream;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            getImagen().setArchivo(buffer);
            fileServicio.ingresarFile(imagen);
            nuevaInfoCurso.setImagen(imagen);            
            inputStream.close();
        } catch (IOException e) {
        	ponerMensajeError("Error - Upload error " + e.getMessage(), "");
        }
    }

    public static String StringClean(String input) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC_";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }
    
	public InformacionCurso getNuevaInfoCurso() {
		return nuevaInfoCurso;
	}

	public void setNuevaInfoCurso(InformacionCurso nuevaInfoCurso) {
		this.nuevaInfoCurso = nuevaInfoCurso;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public InformacionCurso getEliminarInfoCurso() {
		return eliminarInfoCurso;
	}

	public void setEliminarInfoCurso(InformacionCurso eliminarInfoCurso) {
		this.eliminarInfoCurso = eliminarInfoCurso;
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

	public List<InformacionCurso> getListadoInfoCurso() {
		return listadoInfoCurso;
	}

	public void setListadoInfoCurso(List<InformacionCurso> listadoInfoCurso) {
		this.listadoInfoCurso = listadoInfoCurso;
	}

	public boolean isEditarInfoCurso() {
		return editarInfoCurso;
	}

	public void setEditarInfoCurso(boolean editarInfoCurso) {
		this.editarInfoCurso = editarInfoCurso;
	}

	public Bitacora getBitacora() {
		return bitacora;
	}

	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}
}
