/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hportocarrero
 */
@Entity
@Table(name = "archivo_proceso", schema = "gestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivoProceso.findAll", query = "SELECT a FROM ArchivoProceso a")})
public class ArchivoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_archivo_proceso")
    private Integer idArchivoProceso;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne
    private Proceso idProceso;

    public ArchivoProceso() {
    }

    public ArchivoProceso(Integer idArchivoProceso) {
        this.idArchivoProceso = idArchivoProceso;
    }

    public Integer getIdArchivoProceso() {
        return idArchivoProceso;
    }

    public void setIdArchivoProceso(Integer idArchivoProceso) {
        this.idArchivoProceso = idArchivoProceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivoProceso != null ? idArchivoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchivoProceso)) {
            return false;
        }
        ArchivoProceso other = (ArchivoProceso) object;
        if ((this.idArchivoProceso == null && other.idArchivoProceso != null) || (this.idArchivoProceso != null && !this.idArchivoProceso.equals(other.idArchivoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion.academica.modelo.ArchivoProceso[ idArchivoProceso=" + idArchivoProceso + " ]";
    }
    
}
