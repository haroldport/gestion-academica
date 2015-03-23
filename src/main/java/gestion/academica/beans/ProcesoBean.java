package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Proceso;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class ProcesoBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ProcesoBean.class.getName());
	
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	
	private boolean skip;
	private List<CatalogoDetalle> tiposCompra;
	private List<CatalogoDetalle> partidas;
	private List<CatalogoDetalle> saldos;
	private List<CatalogoDetalle> encargadosProceso;
	private List<CatalogoDetalle> tiposProceso;
	private Proceso nuevoProceso;
	private Date fechaActual;
	
	@PostConstruct
	public void iniciar() {
		try {
			obtenerCatalogos();
			setearValoresProceso();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	public String onFlowProcess(FlowEvent event) {		
        if(skip) {
            skip = false;
            return "paso4";
        }
        else {
        	if(event.getNewStep().equals("paso3")){
        		setFechaActual(new Date());
        		nuevoProceso.setIdTipoCompra(catalogoDetalleServicio.obtenerPorId(nuevoProceso.getIdTipoCompra().getIdCatalogoDetalle()));
        		nuevoProceso.setIdTipoProceso(catalogoDetalleServicio.obtenerPorId(nuevoProceso.getIdTipoProceso().getIdCatalogoDetalle()));
        	}
        	return event.getNewStep();
        }
    }
	
	private void obtenerCatalogos(){
		tiposCompra = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_COMPRA.getNemonico());
		partidas = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.PARTIDA_PRESUPUESTARIA.getNemonico());
		saldos = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.SALDO.getNemonico());
		encargadosProceso = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.ENCARGADO_PROCESO.getNemonico());
		tiposProceso = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_PROCESO.getNemonico());
	}
	
	private void setearValoresProceso(){
		nuevoProceso = new Proceso();
		nuevoProceso.setIdEncargadoProceso(new CatalogoDetalle());
		nuevoProceso.setIdPartidaPresupuestaria(new CatalogoDetalle());
		nuevoProceso.setIdSaldo(new CatalogoDetalle());
		nuevoProceso.setIdTipoCompra(new CatalogoDetalle());
		nuevoProceso.setIdTipoProceso(new CatalogoDetalle());
	}
	
	public void limpiarFechas(){
		nuevoProceso.setFechaEstimadaAdjudicacion(null);
		nuevoProceso.setFechaFinPuja(null);
		nuevoProceso.setFechaInicioPuja(null);
		nuevoProceso.setFechaLimiteCalificacion(null);
		nuevoProceso.setFechaLimiteEntregaOferta(null);
		nuevoProceso.setFechaLimitePreguntas(null);
		nuevoProceso.setFechaLimiteRespuestaConvalidacion(null);
		nuevoProceso.setFechaLimiteRespuestas(null);
		nuevoProceso.setFechaLimiteSolicitarConvalidacion(null);
		nuevoProceso.setFechaPublicacion(null);
	}
	
	public void validarFechaPublicacion(){
		if(nuevoProceso.getFechaPublicacion().before(fechaActual)){
			ponerMensajeInfo("La fecha de publicación debe ser posterior a la fecha actual", "");
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<CatalogoDetalle> getTiposCompra() {
		return tiposCompra;
	}

	public void setTiposCompra(List<CatalogoDetalle> tiposCompra) {
		this.tiposCompra = tiposCompra;
	}

	public List<CatalogoDetalle> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<CatalogoDetalle> partidas) {
		this.partidas = partidas;
	}

	public List<CatalogoDetalle> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<CatalogoDetalle> saldos) {
		this.saldos = saldos;
	}

	public List<CatalogoDetalle> getEncargadosProceso() {
		return encargadosProceso;
	}

	public void setEncargadosProceso(List<CatalogoDetalle> encargadosProceso) {
		this.encargadosProceso = encargadosProceso;
	}

	public Proceso getNuevoProceso() {
		return nuevoProceso;
	}

	public void setNuevoProceso(Proceso nuevoProceso) {
		this.nuevoProceso = nuevoProceso;
	}

	public List<CatalogoDetalle> getTiposProceso() {
		return tiposProceso;
	}

	public void setTiposProceso(List<CatalogoDetalle> tiposProceso) {
		this.tiposProceso = tiposProceso;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	
}
