package gestion.academica.beans;

import gestion.academica.enumerado.CatalogoEnum;
import gestion.academica.modelo.ArchivoProceso;
import gestion.academica.modelo.CatalogoDetalle;
import gestion.academica.modelo.CodigosSercop;
import gestion.academica.modelo.Proceso;
import gestion.academica.modelo.ProductoProceso;
import gestion.academica.servicio.CatalogoDetalleServicio;
import gestion.academica.servicio.CodigosSercopServicio;
import gestion.academica.servicio.ProcesoServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ProcesoBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ProcesoBean.class.getName());
	
	@EJB
	private CatalogoDetalleServicio catalogoDetalleServicio;
	@EJB
	private CodigosSercopServicio codigosSercopServicio;
	@EJB
	private ProcesoServicio procesoServicio;
	
	private boolean skip;
	private List<CatalogoDetalle> tiposCompra;
	private List<CatalogoDetalle> partidas;
	private List<CatalogoDetalle> saldos;
	private List<CatalogoDetalle> encargadosProceso;
	private List<CatalogoDetalle> tiposProceso;
	private Proceso nuevoProceso;
	private Date fechaActual;
	private ArchivoProceso nuevoArchivoProceso;
	private List<ArchivoProceso> listaArchivosProceso;
	private ArchivoProceso nuevoArchivoProcesoOpcional;
	private List<ArchivoProceso> listaArchivosProcesoOpcional;
	private UploadedFile file;
	private UploadedFile fileOpcional;
	private CodigosSercop loteSeleccionado;
	private Double totalLotes;
	private String palabraclave;
	private String codigoDeLote;
	private List<CodigosSercop> listadoCodigosPadres;
	private boolean mostrarLote;
	private ProductoProceso nuevoProductoProceso;
	private ProductoProceso nuevoProductoProcesoTmp;
	private List<ProductoProceso> listaProductosProceso;
	private CodigosSercop productoSeleccionado;
	private List<CodigosSercop> listaCodigosHijos;
	private boolean editarProductoProceso;
	private Double subtotalTmp;
	
	@PostConstruct
	public void iniciar() {
		try {
			obtenerCatalogos();
			setearValoresProceso();
			setearValoresArchivo();
			setearValoresArchivoOpcional();
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
        	if(event.getOldStep().equals("paso2") && !verificarTotal()){
        		skip = false;
        		return "paso2";
        	}else{
        		return event.getNewStep();
        	}
        }
    }
	
	public void inicializarBusquedaLote(){
		loteSeleccionado = new CodigosSercop();
		setPalabraclave("");
		setCodigoDeLote("");
		listadoCodigosPadres = new ArrayList<>();
		setMostrarLote(Boolean.FALSE);
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
		nuevoProceso.getIdTipoCompra().setIdCatalogoDetalle(243);
		nuevoProceso.setIdTipoProceso(new CatalogoDetalle());
		nuevoProceso.getIdTipoProceso().setIdCatalogoDetalle(237);
		listaArchivosProceso = new ArrayList<>();
		listaArchivosProcesoOpcional = new ArrayList<>();
		loteSeleccionado = new CodigosSercop();
		totalLotes = 0.0;
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
	
	public void setearValoresArchivo(){
		nuevoArchivoProceso = new ArchivoProceso();
		nuevoArchivoProceso.setIdProceso(new Proceso());
	}
	
	public void setearValoresArchivoOpcional(){
		nuevoArchivoProcesoOpcional = new ArchivoProceso();
		nuevoArchivoProcesoOpcional.setIdProceso(new Proceso());
	}
	
	public void upload() {		
        if(file != null) {
        	nuevoArchivoProceso.setNombreArchivo(file.getFileName());        	
        	nuevoArchivoProceso.setFechaRegistro(new Date());
        	listaArchivosProceso.add(nuevoArchivoProceso);
            setearValoresArchivo();
        }
    }
	
	public void uploadOpcional() {		
        if(fileOpcional != null) {
        	nuevoArchivoProcesoOpcional.setNombreArchivo(fileOpcional.getFileName());
        	nuevoArchivoProcesoOpcional.setFechaRegistro(new Date());
        	listaArchivosProcesoOpcional.add(nuevoArchivoProcesoOpcional);
            setearValoresArchivoOpcional();
        }
    }
	
	public void quitarArchivo(ArchivoProceso archivo){
		Iterator<ArchivoProceso> it = listaArchivosProceso.iterator();
        while (it.hasNext()) {
        	ArchivoProceso ap = (ArchivoProceso) it.next();
        	if(ap.getFechaRegistro().equals(archivo.getFechaRegistro())){
        		it.remove();
        	}
        }
	}
	
	public void quitarArchivoOpcional(ArchivoProceso archivo){
		Iterator<ArchivoProceso> it = listaArchivosProcesoOpcional.iterator();
        while (it.hasNext()) {
        	ArchivoProceso ap = (ArchivoProceso) it.next();
        	if(ap.getFechaRegistro().equals(archivo.getFechaRegistro())){
        		it.remove();
        	}
        }
	}
	
	public void buscarPorPalabraClave(){
		try {
			listadoCodigosPadres = codigosSercopServicio.buscarPorPalabraClave(palabraclave);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buscarPorCodigoLote(){
		try {
			listadoCodigosPadres = codigosSercopServicio.buscarPorCodigoLote(codigoDeLote);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seleccionarLote(SelectEvent event) {
		loteSeleccionado = (CodigosSercop) event.getObject();
    }
	
	private void iniciarProductoProceso(){
		nuevoProductoProceso = new ProductoProceso();
		nuevoProductoProceso.setCodigo(new CodigosSercop());
		nuevoProductoProceso.setIdProceso(new Proceso());
		nuevoProductoProcesoTmp = new ProductoProceso();
		nuevoProductoProcesoTmp.setCodigo(new CodigosSercop());
		nuevoProductoProcesoTmp.setIdProceso(new Proceso());
		setSubtotalTmp(0.0);
	}
	
	public void mostrarLoteSeleccionado(){
		if(loteSeleccionado != null){
			setTotalLotes(0.0);
			listaProductosProceso = new ArrayList<>();
			iniciarProductoProceso();
			setListaCodigosHijos(loteSeleccionado.getCodigosSercopList());
			setMostrarLote(Boolean.TRUE);
		}
	}
	
	public void seleccionarProducto(SelectEvent event){
		productoSeleccionado = (CodigosSercop) event.getObject();
		boolean existe = false;
		if(listaProductosProceso.size() > 0){
			for(ProductoProceso pr : listaProductosProceso){
				if(pr.getCodigo().getCodigo().equals(productoSeleccionado.getCodigo())){
					existe = true;
					break;
				}
			}
			if(existe){
				ponerMensajeError("El producto ya fue seleccionado", "");
			}else{
				nuevoProductoProceso.setCodigo(productoSeleccionado);
			}
		}else{
			nuevoProductoProceso.setCodigo(productoSeleccionado);
		}	
		setEditarProductoProceso(Boolean.FALSE);
	}
	
	public void calcularSubtotal(){
		if(nuevoProductoProceso.getPrecioUnitario() != null && nuevoProductoProceso.getCantidad() != null){
			nuevoProductoProceso.setSubtotal(nuevoProductoProceso.getPrecioUnitario() * 
					nuevoProductoProceso.getCantidad());
		}		
	}
	
	public void agregarProducto(){
		listaProductosProceso.add(nuevoProductoProceso);
		setTotalLotes(getTotalLotes() + nuevoProductoProceso.getSubtotal());
		iniciarProductoProceso();
	}
	
	public String editarProductoProceso(ProductoProceso producto) {
		setEditarProductoProceso(Boolean.TRUE);
		setSubtotalTmp(producto.getSubtotal());
		setNuevoProductoProceso(producto);
		setNuevoProductoProcesoTmp(producto);
		return "";
	}
	
	public void editarProductoProceso(){
		setEditarProductoProceso(Boolean.FALSE);
		for(ProductoProceso pr : listaProductosProceso){
			if(pr.equals(nuevoProductoProcesoTmp)){
				pr = nuevoProductoProceso;
			}
		}
		setTotalLotes((getTotalLotes() - subtotalTmp) + nuevoProductoProceso.getSubtotal());
		iniciarProductoProceso();
	}
	
	public String eliminarProductoProceso(ProductoProceso producto) {
		Iterator<ProductoProceso> it = listaProductosProceso.iterator();
		Double subtotal = 0.0;
        while (it.hasNext()) {
        	ProductoProceso pr = (ProductoProceso) it.next();
        	if(pr.getCodigo().getCodigo().equals(producto.getCodigo().getCodigo())){
        		subtotal = pr.getSubtotal();
        		it.remove();
        	}
        }
		setTotalLotes(getTotalLotes() - subtotal);
		return "";
	}
	
	public boolean verificarTotal(){
		boolean result = true;
		if( totalLotes > nuevoProceso.getPresupuestoReferencial() || totalLotes < nuevoProceso.getPresupuestoReferencial()){
			result = false;
		}
		return result;
	}
	
	public void finalizarProceso(){
		if(nuevoProceso.getIdProceso() == null){
			nuevoProceso.setIdUsuario(this.getUsuario());
			if(listaArchivosProcesoOpcional.size() > 0){
				listaArchivosProceso.addAll(listaArchivosProcesoOpcional);
			}
			procesoServicio.guardarProceso(nuevoProceso, listaArchivosProceso, listaProductosProceso);
			ponerMensajeInfo("El proceso fue creado correctamente", "");
			setSkip(Boolean.TRUE);
		}else{
			ponerMensajeError("El proceso YA fue creado", "");
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

	public ArchivoProceso getNuevoArchivoProceso() {
		return nuevoArchivoProceso;
	}

	public void setNuevoArchivoProceso(ArchivoProceso nuevoArchivoProceso) {
		this.nuevoArchivoProceso = nuevoArchivoProceso;
	}

	public List<ArchivoProceso> getListaArchivosProceso() {
		return listaArchivosProceso;
	}

	public void setListaArchivosProceso(List<ArchivoProceso> listaArchivosProceso) {
		this.listaArchivosProceso = listaArchivosProceso;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFile getFileOpcional() {
		return fileOpcional;
	}

	public void setFileOpcional(UploadedFile fileOpcional) {
		this.fileOpcional = fileOpcional;
	}

	public ArchivoProceso getNuevoArchivoProcesoOpcional() {
		return nuevoArchivoProcesoOpcional;
	}

	public void setNuevoArchivoProcesoOpcional(
			ArchivoProceso nuevoArchivoProcesoOpcional) {
		this.nuevoArchivoProcesoOpcional = nuevoArchivoProcesoOpcional;
	}

	public List<ArchivoProceso> getListaArchivosProcesoOpcional() {
		return listaArchivosProcesoOpcional;
	}

	public void setListaArchivosProcesoOpcional(
			List<ArchivoProceso> listaArchivosProcesoOpcional) {
		this.listaArchivosProcesoOpcional = listaArchivosProcesoOpcional;
	}

	public CodigosSercop getLoteSeleccionado() {
		return loteSeleccionado;
	}

	public void setLoteSeleccionado(CodigosSercop loteSeleccionado) {
		this.loteSeleccionado = loteSeleccionado;
	}

	public Double getTotalLotes() {
		return totalLotes;
	}

	public void setTotalLotes(Double totalLotes) {
		this.totalLotes = totalLotes;
	}

	public String getPalabraclave() {
		return palabraclave;
	}

	public void setPalabraclave(String palabraclave) {
		this.palabraclave = palabraclave;
	}

	public String getCodigoDeLote() {
		return codigoDeLote;
	}

	public void setCodigoDeLote(String codigoDeLote) {
		this.codigoDeLote = codigoDeLote;
	}

	public List<CodigosSercop> getListadoCodigosPadres() {
		return listadoCodigosPadres;
	}

	public void setListadoCodigosPadres(List<CodigosSercop> listadoCodigosPadres) {
		this.listadoCodigosPadres = listadoCodigosPadres;
	}

	public boolean isMostrarLote() {
		return mostrarLote;
	}

	public void setMostrarLote(boolean mostrarLote) {
		this.mostrarLote = mostrarLote;
	}

	public ProductoProceso getNuevoProductoProceso() {
		return nuevoProductoProceso;
	}

	public void setNuevoProductoProceso(ProductoProceso nuevoProductoProceso) {
		this.nuevoProductoProceso = nuevoProductoProceso;
	}

	public List<ProductoProceso> getListaProductosProceso() {
		return listaProductosProceso;
	}

	public void setListaProductosProceso(List<ProductoProceso> listaProductosProceso) {
		this.listaProductosProceso = listaProductosProceso;
	}

	public CodigosSercop getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(CodigosSercop productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<CodigosSercop> getListaCodigosHijos() {
		return listaCodigosHijos;
	}

	public void setListaCodigosHijos(List<CodigosSercop> listaCodigosHijos) {
		this.listaCodigosHijos = listaCodigosHijos;
	}

	public boolean isEditarProductoProceso() {
		return editarProductoProceso;
	}

	public void setEditarProductoProceso(boolean editarProductoProceso) {
		this.editarProductoProceso = editarProductoProceso;
	}

	public ProductoProceso getNuevoProductoProcesoTmp() {
		return nuevoProductoProcesoTmp;
	}

	public void setNuevoProductoProcesoTmp(ProductoProceso nuevoProductoProcesoTmp) {
		this.nuevoProductoProcesoTmp = nuevoProductoProcesoTmp;
	}

	public Double getSubtotalTmp() {
		return subtotalTmp;
	}

	public void setSubtotalTmp(Double subtotalTmp) {
		this.subtotalTmp = subtotalTmp;
	}
	
}
