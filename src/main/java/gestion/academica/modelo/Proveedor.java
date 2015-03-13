package gestion.academica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge Rivera
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")})
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 100)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 100)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 100)
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_inicio")
    private int anioInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "domicilio_tributario")
    private String domicilioTributario;
    @Size(max = 100)
    @Column(name = "interseccion_referencia")
    private String interseccionReferencia;
    @Column(name = "numero_calle")
    private String numeroCalle;
    @Size(max = 80)
    @Column(name = "edificio_referencia")
    private String edificioReferencia;
    @Size(max = 80)
    @Column(name = "departamento_oficina")
    private String departamentoOficina;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 15)
    @Column(name = "celular")
    private String celular;
    @Size(max = 100)
    @Column(name = "primer_apellido_contacto")
    private String primerApellidoContacto;
    @Size(max = 100)
    @Column(name = "segundo_apellido_contacto")
    private String segundoApellidoContacto;
    @Size(max = 100)
    @Column(name = "primer_nombre_contacto")
    private String primerNombreContacto;
    @Size(max = 100)
    @Column(name = "segundo_nombre_contacto")
    private String segundoNombreContacto;
    @Size(max = 15)
    @Column(name = "numero_identificacion_contacto")
    private String numeroIdentificacionContacto;
    @Column(name = "fecha_nacimiento_contacto")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoContacto;
    @Column(name = "cumple_normativa")
    private Boolean cumpleNormativa;
    @Column(name = "porcentaje_exporta")
    private Integer porcentajeExporta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "activos_totales")
    private Double activosTotales;
    @Column(name = "pasivos_totales")
    private Double pasivosTotales;
    @Column(name = "patrimonio")
    private Double patrimonio;
    @Column(name = "porcentaje_agregado_nacional")
    private Integer porcentajeAgregadoNacional;
    @Column(name = "anio_fiscal")
    private Integer anioFiscal;
    @JoinColumn(name = "id_area_especialidad", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idAreaEspecialidad;
    @JoinColumn(name = "id_ventas_brutas", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idVentasBrutas;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idTipoPersona;
    @JoinColumn(name = "id_sexo_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idSexoContacto;
    @JoinColumn(name = "id_residente_ecuador", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idResidenteEcuador;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idProvincia;
    @JoinColumn(name = "id_producto_seleccionado", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idProductoSeleccionado;
    @JoinColumn(name = "id_parroquia", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idParroquia;
    @JoinColumn(name = "id_area_ocupacional_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idAreaOcupacionalContacto;
    @JoinColumn(name = "id_canton", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idCanton;
    @JoinColumn(name = "id_cargo_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idCargoContacto;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idCiudad;
    @JoinColumn(name = "id_documento_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idDocumentoContacto;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idEstadoCivil;
    @JoinColumn(name = "id_estado_civil_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idEstadoCivilContacto;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idGenero;
    @JoinColumn(name = "id_nivel_educacion", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idNivelEducacion;
    @JoinColumn(name = "id_nivel_educacion_contacto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idNivelEducacionContacto;
    @JoinColumn(name = "id_numero_trabajadores", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idNumeroTrabajadores;
    @JoinColumn(name = "id_origen", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idOrigen;
    @Transient
    private String confirmarClave;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, String usuario, String clave, String correo, String ruc, String primerApellido, String primerNombre, String nombreComercial, int anioInicio, String domicilioTributario, String numeroCalle) {
        this.idProveedor = idProveedor;
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.ruc = ruc;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.nombreComercial = nombreComercial;
        this.anioInicio = anioInicio;
        this.domicilioTributario = domicilioTributario;
        this.numeroCalle = numeroCalle;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }

    public String getDomicilioTributario() {
        return domicilioTributario;
    }

    public void setDomicilioTributario(String domicilioTributario) {
        this.domicilioTributario = domicilioTributario;
    }

    public String getInterseccionReferencia() {
        return interseccionReferencia;
    }

    public void setInterseccionReferencia(String interseccionReferencia) {
        this.interseccionReferencia = interseccionReferencia;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getEdificioReferencia() {
        return edificioReferencia;
    }

    public void setEdificioReferencia(String edificioReferencia) {
        this.edificioReferencia = edificioReferencia;
    }

    public String getDepartamentoOficina() {
        return departamentoOficina;
    }

    public void setDepartamentoOficina(String departamentoOficina) {
        this.departamentoOficina = departamentoOficina;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPrimerApellidoContacto() {
        return primerApellidoContacto;
    }

    public void setPrimerApellidoContacto(String primerApellidoContacto) {
        this.primerApellidoContacto = primerApellidoContacto;
    }

    public String getSegundoApellidoContacto() {
        return segundoApellidoContacto;
    }

    public void setSegundoApellidoContacto(String segundoApellidoContacto) {
        this.segundoApellidoContacto = segundoApellidoContacto;
    }

    public String getPrimerNombreContacto() {
        return primerNombreContacto;
    }

    public void setPrimerNombreContacto(String primerNombreContacto) {
        this.primerNombreContacto = primerNombreContacto;
    }

    public String getSegundoNombreContacto() {
        return segundoNombreContacto;
    }

    public void setSegundoNombreContacto(String segundoNombreContacto) {
        this.segundoNombreContacto = segundoNombreContacto;
    }

    public String getNumeroIdentificacionContacto() {
        return numeroIdentificacionContacto;
    }

    public void setNumeroIdentificacionContacto(String numeroIdentificacionContacto) {
        this.numeroIdentificacionContacto = numeroIdentificacionContacto;
    }

    public Date getFechaNacimientoContacto() {
        return fechaNacimientoContacto;
    }

    public void setFechaNacimientoContacto(Date fechaNacimientoContacto) {
        this.fechaNacimientoContacto = fechaNacimientoContacto;
    }

    public Boolean getCumpleNormativa() {
        return cumpleNormativa;
    }

    public void setCumpleNormativa(Boolean cumpleNormativa) {
        this.cumpleNormativa = cumpleNormativa;
    }

    public Integer getPorcentajeExporta() {
        return porcentajeExporta;
    }

    public void setPorcentajeExporta(Integer porcentajeExporta) {
        this.porcentajeExporta = porcentajeExporta;
    }

    public Double getActivosTotales() {
        return activosTotales;
    }

    public void setActivosTotales(Double activosTotales) {
        this.activosTotales = activosTotales;
    }

    public Double getPasivosTotales() {
        return pasivosTotales;
    }

    public void setPasivosTotales(Double pasivosTotales) {
        this.pasivosTotales = pasivosTotales;
    }

    public Double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getPorcentajeAgregadoNacional() {
        return porcentajeAgregadoNacional;
    }

    public void setPorcentajeAgregadoNacional(Integer porcentajeAgregadoNacional) {
        this.porcentajeAgregadoNacional = porcentajeAgregadoNacional;
    }

    public Integer getAnioFiscal() {
        return anioFiscal;
    }

    public void setAnioFiscal(Integer anioFiscal) {
        this.anioFiscal = anioFiscal;
    }

    public CatalogoDetalle getIdAreaEspecialidad() {
        return idAreaEspecialidad;
    }

    public void setIdAreaEspecialidad(CatalogoDetalle idAreaEspecialidad) {
        this.idAreaEspecialidad = idAreaEspecialidad;
    }

    public CatalogoDetalle getIdVentasBrutas() {
        return idVentasBrutas;
    }

    public void setIdVentasBrutas(CatalogoDetalle idVentasBrutas) {
        this.idVentasBrutas = idVentasBrutas;
    }

    public CatalogoDetalle getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(CatalogoDetalle idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public CatalogoDetalle getIdSexoContacto() {
        return idSexoContacto;
    }

    public void setIdSexoContacto(CatalogoDetalle idSexoContacto) {
        this.idSexoContacto = idSexoContacto;
    }

    public CatalogoDetalle getIdResidenteEcuador() {
        return idResidenteEcuador;
    }

    public void setIdResidenteEcuador(CatalogoDetalle idResidenteEcuador) {
        this.idResidenteEcuador = idResidenteEcuador;
    }

    public CatalogoDetalle getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(CatalogoDetalle idProvincia) {
        this.idProvincia = idProvincia;
    }

    public CatalogoDetalle getIdProductoSeleccionado() {
        return idProductoSeleccionado;
    }

    public void setIdProductoSeleccionado(CatalogoDetalle idProductoSeleccionado) {
        this.idProductoSeleccionado = idProductoSeleccionado;
    }

    public CatalogoDetalle getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(CatalogoDetalle idParroquia) {
        this.idParroquia = idParroquia;
    }

    public CatalogoDetalle getIdAreaOcupacionalContacto() {
        return idAreaOcupacionalContacto;
    }

    public void setIdAreaOcupacionalContacto(CatalogoDetalle idAreaOcupacionalContacto) {
        this.idAreaOcupacionalContacto = idAreaOcupacionalContacto;
    }

    public CatalogoDetalle getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(CatalogoDetalle idCanton) {
        this.idCanton = idCanton;
    }

    public CatalogoDetalle getIdCargoContacto() {
        return idCargoContacto;
    }

    public void setIdCargoContacto(CatalogoDetalle idCargoContacto) {
        this.idCargoContacto = idCargoContacto;
    }

    public CatalogoDetalle getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(CatalogoDetalle idCiudad) {
        this.idCiudad = idCiudad;
    }

    public CatalogoDetalle getIdDocumentoContacto() {
        return idDocumentoContacto;
    }

    public void setIdDocumentoContacto(CatalogoDetalle idDocumentoContacto) {
        this.idDocumentoContacto = idDocumentoContacto;
    }

    public CatalogoDetalle getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(CatalogoDetalle idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public CatalogoDetalle getIdEstadoCivilContacto() {
        return idEstadoCivilContacto;
    }

    public void setIdEstadoCivilContacto(CatalogoDetalle idEstadoCivilContacto) {
        this.idEstadoCivilContacto = idEstadoCivilContacto;
    }

    public CatalogoDetalle getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(CatalogoDetalle idGenero) {
        this.idGenero = idGenero;
    }

    public CatalogoDetalle getIdNivelEducacion() {
        return idNivelEducacion;
    }

    public void setIdNivelEducacion(CatalogoDetalle idNivelEducacion) {
        this.idNivelEducacion = idNivelEducacion;
    }

    public CatalogoDetalle getIdNivelEducacionContacto() {
        return idNivelEducacionContacto;
    }

    public void setIdNivelEducacionContacto(CatalogoDetalle idNivelEducacionContacto) {
        this.idNivelEducacionContacto = idNivelEducacionContacto;
    }

    public CatalogoDetalle getIdNumeroTrabajadores() {
        return idNumeroTrabajadores;
    }

    public void setIdNumeroTrabajadores(CatalogoDetalle idNumeroTrabajadores) {
        this.idNumeroTrabajadores = idNumeroTrabajadores;
    }

    public CatalogoDetalle getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(CatalogoDetalle idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.academica.modelo.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
