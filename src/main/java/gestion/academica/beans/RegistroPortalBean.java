package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.Proveedor;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<CatalogoDetalle> generos;
	private List<CatalogoDetalle> estadosCiviles;
	private List<CatalogoDetalle> nivelesEducacion;
	private List<CatalogoDetalle> areasEspecialidad;
	private List<Integer> anios;
	private List<CatalogoDetalle> provincias;
    private List<CatalogoDetalle> cantones;
    private List<CatalogoDetalle> parroquias;
    private List<CatalogoDetalle> tiposTelefono;
	
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
		nuevoProveedor.setIdGenero(new CatalogoDetalle());
		nuevoProveedor.setIdEstadoCivil(new CatalogoDetalle());
		nuevoProveedor.setIdNivelEducacion(new CatalogoDetalle());
		nuevoProveedor.setIdAreaEspecialidad(new CatalogoDetalle());
		nuevoProveedor.setIdProvincia(new CatalogoDetalle());
		nuevoProveedor.setIdCanton(new CatalogoDetalle());
		nuevoProveedor.setIdParroquia(new CatalogoDetalle());
	}
	
	private void obtenerCatalogos(){
		tiposPersona = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_PERSONA2.getNemonico());
		origenes = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.ORIGEN.getNemonico());
		residentesEcuador = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.RESIDENTE_ECUADOR.getNemonico());
		domiciliadoEcuador = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.DOMICILIADO_ECUADOR.getNemonico());
		generos = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.GENERO.getNemonico());
		estadosCiviles = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.ESTADO_CIVIL.getNemonico());
		nivelesEducacion = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.NIVEL_EDUCACION.getNemonico());
		areasEspecialidad = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.AREA_ESPECIALIDAD.getNemonico());
		anios = new ArrayList<>();
		for(int i = 1900; i < 2021; i++){
			anios.add(i);
		}
		provincias = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.PROVINCIA.getNemonico());
		tiposTelefono = catalogoDetalleServicio.obtenerPorCatalogoNemonico(CatalogoEnum.TIPO_TELEFONO.getNemonico());
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
	
	public void obtenerCantones() {
        cantones = new ArrayList<>();
        cantones = catalogoDetalleServicio.obtenerPorCatalogoNemonicoYPadre(CatalogoEnum.CANTON.getNemonico(),
                nuevoProveedor.getIdProvincia().getIdCatalogoDetalle());
        if (cantones == null) {
            cantones = catalogoDetalleServicio.obtenerPorCatDetNemonico("OTR");
        }
    }
	
	public void obtenerParroquias() {
        parroquias = new ArrayList<>();
        parroquias = catalogoDetalleServicio.obtenerPorCatalogoNemonicoYPadre(CatalogoEnum.PARROQUIA.getNemonico(),
                nuevoProveedor.getIdCanton().getIdCatalogoDetalle());
        if (parroquias == null) {
            parroquias = catalogoDetalleServicio.obtenerPorCatDetNemonico("OTR");
        }
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

	public List<CatalogoDetalle> getGeneros() {
		return generos;
	}

	public void setGeneros(List<CatalogoDetalle> generos) {
		this.generos = generos;
	}

	public List<CatalogoDetalle> getEstadosCiviles() {
		return estadosCiviles;
	}

	public void setEstadosCiviles(List<CatalogoDetalle> estadosCiviles) {
		this.estadosCiviles = estadosCiviles;
	}

	public List<CatalogoDetalle> getNivelesEducacion() {
		return nivelesEducacion;
	}

	public void setNivelesEducacion(List<CatalogoDetalle> nivelesEducacion) {
		this.nivelesEducacion = nivelesEducacion;
	}

	public List<CatalogoDetalle> getAreasEspecialidad() {
		return areasEspecialidad;
	}

	public void setAreasEspecialidad(List<CatalogoDetalle> areasEspecialidad) {
		this.areasEspecialidad = areasEspecialidad;
	}

	public List<Integer> getAnios() {
		return anios;
	}

	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}

	public List<CatalogoDetalle> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<CatalogoDetalle> provincias) {
		this.provincias = provincias;
	}

	public List<CatalogoDetalle> getCantones() {
		return cantones;
	}

	public void setCantones(List<CatalogoDetalle> cantones) {
		this.cantones = cantones;
	}

	public List<CatalogoDetalle> getParroquias() {
		return parroquias;
	}

	public void setParroquias(List<CatalogoDetalle> parroquias) {
		this.parroquias = parroquias;
	}

	public List<CatalogoDetalle> getTiposTelefono() {
		return tiposTelefono;
	}

	public void setTiposTelefono(List<CatalogoDetalle> tiposTelefono) {
		this.tiposTelefono = tiposTelefono;
	}
	
}
