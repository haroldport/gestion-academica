package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Bitacora;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.InformacionCurso;
import gestion.academica.modelo.TemaCurso;
import gestion.academica.servicio.BitacoraServicio;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.InformacionCursoServicio;
import gestion.academica.servicio.TemaCursoServicio;
import gestion.academica.utilitario.Utilitario;

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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class TemaCursoBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private BitacoraServicio bitacoraServicio;
	@EJB
	private TemaCursoServicio temaCursoServicio;
	@EJB
	private InformacionCursoServicio informacionCursoServicio;
	
	private TreeNode rootTemaCurso;
	
	private List<TemaCurso> listaTemasCurso;
	private TemaCurso nuevoTemaCurso;
	private TemaCurso temaCursoPadre;
	private TemaCurso eliminarTemaCurso;
	private Estado estadoActivo;
	private Estado estadoInactivo;
	private static final Logger LOGGER = Logger.getLogger(TemaCursoBean.class.getName());
	private Bitacora bitacora;
	private boolean editarTemaCurso;
	private List<InformacionCurso> listaInformacionCurso;
	
	@PostConstruct
	public void iniciar() {
		try {
			listaTemasCurso = new ArrayList<>();			
			eliminarTemaCurso = new TemaCurso();
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			estadoInactivo = estadoServicio.buscarPorNemonico(EstadoEnum.INACTIVO.getNemonico());
			listaInformacionCurso = informacionCursoServicio.listarInformacionDeCursos();
			rootTemaCurso = new DefaultTreeNode("root", null);
			initValores();
			this.setEditarTemaCurso(Boolean.FALSE);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void initValores(){
		nuevoTemaCurso = new TemaCurso();
		nuevoTemaCurso.setInformacionCurso(new InformacionCurso());
		nuevoTemaCurso.setTemaCurso(new TemaCurso());
		temaCursoPadre = new TemaCurso();
		listaTemasCurso = temaCursoServicio.listarTemasCurso();
		llenarArbolTemas();
	}
	
	public void llenarArbolTemas() {
        try {
            setRootTemaCurso(new DefaultTreeNode("root", null));
            cargarArbolEstOrg(listaTemasCurso, getRootTemaCurso());
            System.out.println("rootEstOrg ; " + getRootTemaCurso().getChildCount());
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE, null, e);
        }
    }

    private void cargarArbolEstOrg(List<TemaCurso> listaTemas, TreeNode nodoPadre) {
        try {
            for (TemaCurso tema : listaTemas) {
                TreeNode node = new DefaultTreeNode(tema, nodoPadre);
                if (!tema.getTemaCursos().isEmpty()) {
                    cargarArbolEstOrg(tema.getTemaCursos(), node);
                }
            }
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE, null, e);
        }
    }
    
    public String seleccionarTemaCurso(TemaCurso temaCurso) {
		setEliminarTemaCurso(temaCurso);
		return "";
	}

	public String editarTemaCurso() {
		try {
			Date fechaActualizacion = new Date();
			setEditarTemaCurso(false);
			nuevoTemaCurso.setDescripcion(nuevoTemaCurso.getDescripcion().toUpperCase());
			temaCursoServicio.editar(nuevoTemaCurso);
			bitacora = new Bitacora(fechaActualizacion, "Modificación de tema: " + nuevoTemaCurso.getDescripcion(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			this.ponerMensajeInfo("El tema fue actualizado correctamente","");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
		return "";
	}

	public String editarTemaCurso(TemaCurso temaCurso) {
		setEditarTemaCurso(true);
		setNuevoTemaCurso(temaCurso);
		return "";
	}

	public void guardar() {
		try {
			Date fechaCreacion = new Date();
			if(temaCursoPadre != null && temaCursoPadre.getIdTemaCurso() != null){
				nuevoTemaCurso.setTemaCurso(temaCursoPadre);
			}else{
				nuevoTemaCurso.setTemaCurso(null);
			}
			nuevoTemaCurso.setEstado(estadoActivo);
			nuevoTemaCurso.setDescripcion(nuevoTemaCurso.getDescripcion().toUpperCase());
			temaCursoServicio.crear(nuevoTemaCurso);
			bitacora = new Bitacora(fechaCreacion, "Creación de tema: " + nuevoTemaCurso.getDescripcion(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			this.ponerMensajeInfo("El tema fue creado correctamente", "");
			initValores();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void eliminar() {
		try {
			Date fechaEliminacion = new Date();
			eliminarTemaCurso.setEstado(estadoInactivo);
			temaCursoServicio.editar(eliminarTemaCurso);
			bitacora = new Bitacora(fechaEliminacion, "Eliminación de tema: " + eliminarTemaCurso.getDescripcion(), this.getUsuario());
            bitacoraServicio.crear(bitacora);
			initValores();
			eliminarTemaCurso = new TemaCurso();
			this.ponerMensajeInfo("El tema fue eliminado correctamente", "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		}
	}
	
	public TreeNode getRootTemaCurso() {
		return rootTemaCurso;
	}
	
	public void setRootTemaCurso(TreeNode rootTemaCurso) {
		this.rootTemaCurso = rootTemaCurso;
	}
	
	public List<TemaCurso> getListaTemasCurso() {
		return listaTemasCurso;
	}
	
	public void setListaTemasCurso(List<TemaCurso> listaTemasCurso) {
		this.listaTemasCurso = listaTemasCurso;
	}
	
	public TemaCurso getNuevoTemaCurso() {
		return nuevoTemaCurso;
	}
	
	public void setNuevoTemaCurso(TemaCurso nuevoTemaCurso) {
		this.nuevoTemaCurso = nuevoTemaCurso;
	}
	
	public TemaCurso getEliminarTemaCurso() {
		return eliminarTemaCurso;
	}
	
	public void setEliminarTemaCurso(TemaCurso eliminarTemaCurso) {
		this.eliminarTemaCurso = eliminarTemaCurso;
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
	
	public boolean isEditarTemaCurso() {
		return editarTemaCurso;
	}
	
	public void setEditarTemaCurso(boolean editarTemaCurso) {
		this.editarTemaCurso = editarTemaCurso;
	}

	public List<InformacionCurso> getListaInformacionCurso() {
		return listaInformacionCurso;
	}

	public void setListaInformacionCurso(
			List<InformacionCurso> listaInformacionCurso) {
		this.listaInformacionCurso = listaInformacionCurso;
	}

	public TemaCurso getTemaCursoPadre() {
		return temaCursoPadre;
	}

	public void setTemaCursoPadre(TemaCurso temaCursoPadre) {
		this.temaCursoPadre = temaCursoPadre;
	}

}
