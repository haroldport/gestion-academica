package gestion.academica.beans;

import gestion.academica.modelo.Imagen;
import gestion.academica.modelo.InformacionCurso;
import gestion.academica.servicio.FileServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
	
	@EJB
    private FileServicio fileServicio;
	
	private InformacionCurso nuevaInfoCurso;
	private static final Logger LOGGER = Logger.getLogger(InfoCursosBean.class.getName());
	private static final int BUFFER_SIZE = 200000;
	private Imagen imagen;
	private File result;
	
	@PostConstruct
	public void iniciar() {
		try {
			initValores();		
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevaInfoCurso = new InformacionCurso();
		nuevaInfoCurso.setImagen(new Imagen());
	}
	
	@SuppressWarnings("resource")
	public void  mostrarImagen(FileUploadEvent event) {
		setImagen(new Imagen());
        getImagen().setTipoArchivo(event.getFile().getContentType());
        getImagen().setExtension(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")));
        getImagen().setNombre(StringClean(event.getFile().getFileName().substring(0, event.getFile().getFileName().lastIndexOf("."))));
        System.out.println(getRequest().getSession().getServletContext().getRealPath("/resources/images/" + event.getFile().getFileName()));
        result = new File(getRequest().getSession().getServletContext().getRealPath("/resources/images/" + event.getFile().getFileName()));
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

}
