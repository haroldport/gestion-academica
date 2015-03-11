package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Proveedor;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class RegistroPortalBean extends Utilitario implements Serializable {
	
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	
	private static final long serialVersionUID = 1L;
	private boolean skip;
	private Integer opcion;
	private static final Logger LOGGER = Logger.getLogger(RegistroPortalBean.class.getName());
	private Proveedor nuevoProveedor;
	private List<CatalogoDetalle> tiposPersona;
	private List<CatalogoDetalle> origenes;
	private List<CatalogoDetalle> residentesEcuador;
	private List<CatalogoDetalle> domiciliadoEcuador;
	
	@PostConstruct
	public void iniciar() {
		try {
			opcion = 0;
			obtenerCatalogos();
			setearValoresProveedor();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	private void setearValoresProveedor(){
		nuevoProveedor = new Proveedor();
		nuevoProveedor.setIdTipoPersona(new CatalogoDetalle());
		nuevoProveedor.setIdOrigen(new CatalogoDetalle());
		nuevoProveedor.setIdResidenteEcuador(new CatalogoDetalle());
	}
	
	private void obtenerCatalogos(){
		tiposPersona = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_PERSONA2.getNemonico());
		origenes = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.ORIGEN.getNemonico());
		residentesEcuador = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.RESIDENTE_ECUADOR.getNemonico());
		domiciliadoEcuador = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.DOMICILIADO_ECUADOR.getNemonico());
	}
	
	public String verRegistroProveedores(){
		return "/faces/paginas/procesos/registroProveedores.xhtml?faces-redirect=true";
	}
	
	public String onFlowProcess(FlowEvent event) {		
        if(skip) {
            skip = false;
            return "paso8";
        }
        else {
        	if(opcion == null){
        		opcion = 0;
        	}
        	if(event.getOldStep().equals("paso1") && (opcion != 1)){
        		RequestContext.getCurrentInstance().execute("PF('dlgRegresar').show();");
        		skip = false;
        		return "paso1";
        	}else{
        		return event.getNewStep();
        	}            
        }
    }
	
	public String regresarLogin(){
		return "/faces/paginas/procesos/principal.xhtml?faces-redirect=true";
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Integer getOpcion() {
		return opcion;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}

	public Proveedor getNuevoProveedor() {
		return nuevoProveedor;
	}

	public void setNuevoProveedor(Proveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public List<CatalogoDetalle> getOrigenes() {
		return origenes;
	}

	public void setOrigenes(List<CatalogoDetalle> origenes) {
		this.origenes = origenes;
	}

	public List<CatalogoDetalle> getTiposPersona() {
		return tiposPersona;
	}

	public void setTiposPersona(List<CatalogoDetalle> tiposPersona) {
		this.tiposPersona = tiposPersona;
	}

	public List<CatalogoDetalle> getResidentesEcuador() {
		return residentesEcuador;
	}

	public void setResidentesEcuador(List<CatalogoDetalle> residentesEcuador) {
		this.residentesEcuador = residentesEcuador;
	}

	public List<CatalogoDetalle> getDomiciliadoEcuador() {
		return domiciliadoEcuador;
	}

	public void setDomiciliadoEcuador(List<CatalogoDetalle> domiciliadoEcuador) {
		this.domiciliadoEcuador = domiciliadoEcuador;
	}
	
}
