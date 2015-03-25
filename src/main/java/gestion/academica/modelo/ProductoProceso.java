package gestion.academica.modelo;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "producto_proceso", schema = "gestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoProceso.findAll", query = "SELECT p FROM ProductoProceso p")})
public class ProductoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_proceso")
    private Integer idProductoProceso;
    @Column(name = "id_unidad")
    private BigInteger idUnidad;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "subtotal")
    private Double subtotal;
    @Size(max = 300)
    @Column(name = "caracteristicas_adicionales")
    private String caracteristicasAdicionales;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Proceso idProceso;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private CodigosSercop codigo;

    public ProductoProceso() {
    }

    public ProductoProceso(Integer idProductoProceso) {
        this.idProductoProceso = idProductoProceso;
    }

    public Integer getIdProductoProceso() {
        return idProductoProceso;
    }

    public void setIdProductoProceso(Integer idProductoProceso) {
        this.idProductoProceso = idProductoProceso;
    }

    public BigInteger getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(BigInteger idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getCaracteristicasAdicionales() {
        return caracteristicasAdicionales;
    }

    public void setCaracteristicasAdicionales(String caracteristicasAdicionales) {
        this.caracteristicasAdicionales = caracteristicasAdicionales;
    }

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

    public CodigosSercop getCodigo() {
        return codigo;
    }

    public void setCodigo(CodigosSercop codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoProceso != null ? idProductoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoProceso)) {
            return false;
        }
        ProductoProceso other = (ProductoProceso) object;
        if ((this.idProductoProceso == null && other.idProductoProceso != null) || (this.idProductoProceso != null && !this.idProductoProceso.equals(other.idProductoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.academica.modelo.ProductoProceso[ idProductoProceso=" + idProductoProceso + " ]";
    }
    
}
