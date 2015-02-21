package gestion.academica.beans;

import gestion.academica.enumerado.EstadoEnum;
import gestion.academica.modelo.Estado;
import gestion.academica.modelo.Estudiante;
import gestion.academica.modelo.Llamada;
import gestion.academica.modelo.Matricula;
import gestion.academica.modelo.Preinscripcion;
import gestion.academica.modelo.Rol;
import gestion.academica.servicio.EstadoServicio;
import gestion.academica.servicio.LlamadaServicio;
import gestion.academica.servicio.MatriculaServicio;
import gestion.academica.servicio.PreinscripcionServicio;
import gestion.academica.servicio.RolServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class PreinscripcionBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PreinscripcionServicio preinscripcionServicio;
	@EJB
	private LlamadaServicio llamadaServicio;
	@EJB
	private EstadoServicio estadoServicio;
	@EJB
	private MatriculaServicio matriculaServicio;
	@EJB
	private RolServicio rolServicio;
	
	private List<Preinscripcion> listaPreinscripciones;
	private static final Logger LOGGER = Logger.getLogger(PreinscripcionBean.class.getName());
	private Llamada nuevaLlamada;
	private Preinscripcion preinscripcionSeleccionada;
	private List<Llamada> listaLlamadas;
	private Estado estadoActivo;
	private Rol rolMatriculado;
	
	@PostConstruct
	public void iniciar() {
		try {
			rolMatriculado = rolServicio.obtenerPorNemonico("RMAT");
			estadoActivo = estadoServicio.buscarPorNemonico(EstadoEnum.ACTIVO.getNemonico());
			listaPreinscripciones = preinscripcionServicio.listarPreinscripciones();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	public void seleccionarPreinscripcion(Preinscripcion preinscripcion){
		preinscripcionSeleccionada = new Preinscripcion();
		nuevaLlamada = new Llamada();
		listaLlamadas = new ArrayList<>();
		setPreinscripcionSeleccionada(preinscripcion);
		listaLlamadas = llamadaServicio.listarLlamadasPorClienteYCurso(preinscripcionSeleccionada.getCliente(), preinscripcionSeleccionada.getCurso());
	}
	
	public void guardarGestion() throws Exception{
		Date fechaLlamada = new Date();
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean gestion = false;
		if(nuevaLlamada.getObservaciones() != null){
			gestion = true;
			nuevaLlamada.setCliente(preinscripcionSeleccionada.getCliente());
			nuevaLlamada.setCurso(preinscripcionSeleccionada.getCurso());
			nuevaLlamada.setFecha(fechaLlamada);
			nuevaLlamada.setEstado(estadoActivo);
			llamadaServicio.crear(nuevaLlamada);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La gestión se guardó correctamente!!!", "");
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El campo Gestión realizada no puede estar vacío","");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("gestion", gestion);
	}
	
	public void matricularCliente(){
		Date fechaMatricula = new Date();
		Estudiante estudiante = new Estudiante(preinscripcionSeleccionada.getCliente().getEmail(), preinscripcionSeleccionada.getCliente().getNombres(), 
				preinscripcionSeleccionada.getCliente().getNumeroDocumento(), estadoActivo, preinscripcionSeleccionada.getCliente().getUsuario());
		Matricula matricula = new Matricula(fechaMatricula, preinscripcionSeleccionada.getCurso(), estadoActivo);
		matriculaServicio.crearEstudianteYMatricula(estudiante, matricula, rolMatriculado);
		preinscripcionSeleccionada.setMatriculado(Boolean.TRUE);
		preinscripcionServicio.editar(preinscripcionSeleccionada);
		ponerMensajeInfo("Cliente matriculado correctamente!!!!", "");
	}

	public List<Preinscripcion> getListaPreinscripciones() {
		return listaPreinscripciones;
	}

	public void setListaPreinscripciones(List<Preinscripcion> listaPreinscripciones) {
		this.listaPreinscripciones = listaPreinscripciones;
	}

	public Llamada getNuevaLlamada() {
		return nuevaLlamada;
	}

	public void setNuevaLlamada(Llamada nuevaLlamada) {
		this.nuevaLlamada = nuevaLlamada;
	}

	public Preinscripcion getPreinscripcionSeleccionada() {
		return preinscripcionSeleccionada;
	}

	public void setPreinscripcionSeleccionada(
			Preinscripcion preinscripcionSeleccionada) {
		this.preinscripcionSeleccionada = preinscripcionSeleccionada;
	}

	public List<Llamada> getListaLlamadas() {
		return listaLlamadas;
	}

	public void setListaLlamadas(List<Llamada> listaLlamadas) {
		this.listaLlamadas = listaLlamadas;
	}

	public Estado getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(Estado estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public Rol getRolMatriculado() {
		return rolMatriculado;
	}

	public void setRolMatriculado(Rol rolMatriculado) {
		this.rolMatriculado = rolMatriculado;
	}

}
