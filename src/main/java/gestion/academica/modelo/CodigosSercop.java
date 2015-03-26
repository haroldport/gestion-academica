/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.academica.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ketty
 */
@Entity
@Table(name = "codigos_sercop", schema = "gestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodigosSercop.findAll", query = "SELECT c FROM CodigosSercop c")})
public class CodigosSercop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nivel")
    private Integer nivel;
    @OneToMany(mappedBy = "codigo")
    private List<ProductoProceso> productoProcesoList;
    @OneToMany(mappedBy = "codigoPadre", fetch = FetchType.EAGER)
    private List<CodigosSercop> codigosSercopList;
    @JoinColumn(name = "codigo_padre", referencedColumnName = "codigo")
    @ManyToOne
    private CodigosSercop codigoPadre;

    public CodigosSercop() {
    }

    public CodigosSercop(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public List<ProductoProceso> getProductoProcesoList() {
        return productoProcesoList;
    }

    public void setProductoProcesoList(List<ProductoProceso> productoProcesoList) {
        this.productoProcesoList = productoProcesoList;
    }

    @XmlTransient
    public List<CodigosSercop> getCodigosSercopList() {
        return codigosSercopList;
    }

    public void setCodigosSercopList(List<CodigosSercop> codigosSercopList) {
        this.codigosSercopList = codigosSercopList;
    }

    public CodigosSercop getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(CodigosSercop codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigosSercop)) {
            return false;
        }
        CodigosSercop other = (CodigosSercop) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.academica.modelo.CodigosSercop[ codigo=" + codigo + " ]";
    }
    
}
