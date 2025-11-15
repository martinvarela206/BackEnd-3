/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martindev.inventariolia;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "elementos", catalog = "inventario_lia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elementos.findAll", query = "SELECT e FROM Elementos e"),
    @NamedQuery(name = "Elementos.findByNroLia", query = "SELECT e FROM Elementos e WHERE e.nroLia = :nroLia"),
    @NamedQuery(name = "Elementos.findByNroUnsj", query = "SELECT e FROM Elementos e WHERE e.nroUnsj = :nroUnsj"),
    @NamedQuery(name = "Elementos.findByTipo", query = "SELECT e FROM Elementos e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Elementos.findByDescripcion", query = "SELECT e FROM Elementos e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Elementos.findByCantidad", query = "SELECT e FROM Elementos e WHERE e.cantidad = :cantidad")})
public class Elementos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nro_lia", nullable = false, length = 25)
    private String nroLia;
    @Size(max = 25)
    @Column(name = "nro_unsj", length = 25)
    private String nroUnsj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "tipo", nullable = false, length = 9)
    private String tipo;
    @Size(max = 255)
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @OneToMany(mappedBy = "nroLia")
    private Collection<Movimientos> movimientosCollection;

    public Elementos() {
    }

    public Elementos(String nroLia) {
        this.nroLia = nroLia;
    }

    public Elementos(String nroLia, String tipo) {
        this.nroLia = nroLia;
        this.tipo = tipo;
    }

    public String getNroLia() {
        return nroLia;
    }

    public void setNroLia(String nroLia) {
        this.nroLia = nroLia;
    }

    public String getNroUnsj() {
        return nroUnsj;
    }

    public void setNroUnsj(String nroUnsj) {
        this.nroUnsj = nroUnsj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public Collection<Movimientos> getMovimientosCollection() {
        return movimientosCollection;
    }

    public void setMovimientosCollection(Collection<Movimientos> movimientosCollection) {
        this.movimientosCollection = movimientosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroLia != null ? nroLia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elementos)) {
            return false;
        }
        Elementos other = (Elementos) object;
        if ((this.nroLia == null && other.nroLia != null) || (this.nroLia != null && !this.nroLia.equals(other.nroLia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.martindev.inventariolia.Elementos[ nroLia=" + nroLia + " ]";
    }
    
}
