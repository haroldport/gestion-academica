/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.academica.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hportocarrero
 */
@Entity
@Table(name = "producto_proceso")
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
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Proceso idProceso;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_catalogo_detalle")
    @ManyToOne(optional = false)
    private CatalogoDetalle idProducto;

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

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

    public CatalogoDetalle getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(CatalogoDetalle idProducto) {
        this.idProducto = idProducto;
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
