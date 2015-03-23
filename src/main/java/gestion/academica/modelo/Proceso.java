package gestion.academica.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")})
public class Proceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proceso")
    private Integer idProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo_proceso")
    private String codigoProceso;
    @Size(max = 256)
    @Column(name = "objeto_proceso")
    private String objetoProceso;
    @Size(max = 1000)
    @Column(name = "descripcion_proceso")
    private String descripcionProceso;
    @Size(max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 300)
    @Column(name = "palabras_claves")
    private String palabrasClaves;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "presupuesto_referencial")
    private Double presupuestoReferencial;
    @Column(name = "anticipo")
    private Integer anticipo;
    @Column(name = "variacion_minima")
    private Integer variacionMinima;
    @Column(name = "costo_levantamiento")
    private Integer costoLevantamiento;
    @Column(name = "valor")
    private Double valor;
    @Size(max = 300)
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "vigencia_oferta")
    private Integer vigenciaOferta;
    @Column(name = "plazo_entrega")
    private Integer plazoEntrega;
    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Column(name = "fecha_limite_preguntas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimitePreguntas;
    @Column(name = "fecha_limite_respuestas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteRespuestas;
    @Column(name = "fecha_limite_entrega_oferta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteEntregaOferta;
    @Column(name = "fecha_limite_solicitar_convalidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteSolicitarConvalidacion;
    @Column(name = "fecha_limite_respuesta_convalidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteRespuestaConvalidacion;
    @Column(name = "fecha_limite_calificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteCalificacion;
    @Column(name = "fecha_inicio_puja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioPuja;
    @Column(name = "fecha_fin_puja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPuja;
    @Column(name = "fecha_estimada_adjudicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstimadaAdjudicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
    private List<ProductoProceso> productoProcesoList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_encargado_proceso", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idEncargadoProceso;
    @JoinColumn(name = "id_saldo", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idSaldo;
    @JoinColumn(name = "id_partida_presupuestaria", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idPartidaPresupuestaria;
    @JoinColumn(name = "id_tipo_compra", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idTipoCompra;
    @JoinColumn(name = "id_tipo_proceso", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle idTipoProceso;

    public Proceso() {
    }

    public Proceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Proceso(Integer idProceso, String codigoProceso) {
        this.idProceso = idProceso;
        this.codigoProceso = codigoProceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public String getObjetoProceso() {
        return objetoProceso;
    }

    public void setObjetoProceso(String objetoProceso) {
        this.objetoProceso = objetoProceso;
    }

    public String getDescripcionProceso() {
        return descripcionProceso;
    }

    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(String palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public Double getPresupuestoReferencial() {
        return presupuestoReferencial;
    }

    public void setPresupuestoReferencial(Double presupuestoReferencial) {
        this.presupuestoReferencial = presupuestoReferencial;
    }

    public Integer getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(Integer anticipo) {
        this.anticipo = anticipo;
    }

    public Integer getVariacionMinima() {
        return variacionMinima;
    }

    public void setVariacionMinima(Integer variacionMinima) {
        this.variacionMinima = variacionMinima;
    }

    public Integer getCostoLevantamiento() {
        return costoLevantamiento;
    }

    public void setCostoLevantamiento(Integer costoLevantamiento) {
        this.costoLevantamiento = costoLevantamiento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Integer getVigenciaOferta() {
        return vigenciaOferta;
    }

    public void setVigenciaOferta(Integer vigenciaOferta) {
        this.vigenciaOferta = vigenciaOferta;
    }

    public Integer getPlazoEntrega() {
        return plazoEntrega;
    }

    public void setPlazoEntrega(Integer plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaLimitePreguntas() {
        return fechaLimitePreguntas;
    }

    public void setFechaLimitePreguntas(Date fechaLimitePreguntas) {
        this.fechaLimitePreguntas = fechaLimitePreguntas;
    }

    public Date getFechaLimiteRespuestas() {
        return fechaLimiteRespuestas;
    }

    public void setFechaLimiteRespuestas(Date fechaLimiteRespuestas) {
        this.fechaLimiteRespuestas = fechaLimiteRespuestas;
    }

    public Date getFechaLimiteEntregaOferta() {
        return fechaLimiteEntregaOferta;
    }

    public void setFechaLimiteEntregaOferta(Date fechaLimiteEntregaOferta) {
        this.fechaLimiteEntregaOferta = fechaLimiteEntregaOferta;
    }

    public Date getFechaLimiteSolicitarConvalidacion() {
        return fechaLimiteSolicitarConvalidacion;
    }

    public void setFechaLimiteSolicitarConvalidacion(Date fechaLimiteSolicitarConvalidacion) {
        this.fechaLimiteSolicitarConvalidacion = fechaLimiteSolicitarConvalidacion;
    }

    public Date getFechaLimiteRespuestaConvalidacion() {
        return fechaLimiteRespuestaConvalidacion;
    }

    public void setFechaLimiteRespuestaConvalidacion(Date fechaLimiteRespuestaConvalidacion) {
        this.fechaLimiteRespuestaConvalidacion = fechaLimiteRespuestaConvalidacion;
    }

    public Date getFechaLimiteCalificacion() {
        return fechaLimiteCalificacion;
    }

    public void setFechaLimiteCalificacion(Date fechaLimiteCalificacion) {
        this.fechaLimiteCalificacion = fechaLimiteCalificacion;
    }

    public Date getFechaInicioPuja() {
        return fechaInicioPuja;
    }

    public void setFechaInicioPuja(Date fechaInicioPuja) {
        this.fechaInicioPuja = fechaInicioPuja;
    }

    public Date getFechaFinPuja() {
        return fechaFinPuja;
    }

    public void setFechaFinPuja(Date fechaFinPuja) {
        this.fechaFinPuja = fechaFinPuja;
    }

    public Date getFechaEstimadaAdjudicacion() {
        return fechaEstimadaAdjudicacion;
    }

    public void setFechaEstimadaAdjudicacion(Date fechaEstimadaAdjudicacion) {
        this.fechaEstimadaAdjudicacion = fechaEstimadaAdjudicacion;
    }

    @XmlTransient
    public List<ProductoProceso> getProductoProcesoList() {
        return productoProcesoList;
    }

    public void setProductoProcesoList(List<ProductoProceso> productoProcesoList) {
        this.productoProcesoList = productoProcesoList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CatalogoDetalle getIdEncargadoProceso() {
        return idEncargadoProceso;
    }

    public void setIdEncargadoProceso(CatalogoDetalle idEncargadoProceso) {
        this.idEncargadoProceso = idEncargadoProceso;
    }

    public CatalogoDetalle getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(CatalogoDetalle idSaldo) {
        this.idSaldo = idSaldo;
    }

    public CatalogoDetalle getIdPartidaPresupuestaria() {
        return idPartidaPresupuestaria;
    }

    public void setIdPartidaPresupuestaria(CatalogoDetalle idPartidaPresupuestaria) {
        this.idPartidaPresupuestaria = idPartidaPresupuestaria;
    }

    public CatalogoDetalle getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(CatalogoDetalle idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public CatalogoDetalle getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(CatalogoDetalle idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.academica.modelo.Proceso[ idProceso=" + idProceso + " ]";
    }
    
}
