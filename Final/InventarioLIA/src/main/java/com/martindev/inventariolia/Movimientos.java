/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martindev.inventariolia;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "movimientos", catalog = "inventario_lia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m"),
    @NamedQuery(name = "Movimientos.findById", query = "SELECT m FROM Movimientos m WHERE m.id = :id"),
    @NamedQuery(name = "Movimientos.findByNroUnsj", query = "SELECT m FROM Movimientos m WHERE m.nroUnsj = :nroUnsj"),
    @NamedQuery(name = "Movimientos.findByEstado", query = "SELECT m FROM Movimientos m WHERE m.estado = :estado"),
    @NamedQuery(name = "Movimientos.findByUbicacion", query = "SELECT m FROM Movimientos m WHERE m.ubicacion = :ubicacion"),
    @NamedQuery(name = "Movimientos.findByFecha", query = "SELECT m FROM Movimientos m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Movimientos.findByComentario", query = "SELECT m FROM Movimientos m WHERE m.comentario = :comentario")})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 25)
    @Column(name = "nro_unsj", length = 25)
    private String nroUnsj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "estado", nullable = false, length = 12)
    private String estado;
    @Size(max = 100)
    @Column(name = "ubicacion", length = 100)
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "comentario", length = 255)
    private String comentario;
    @JoinColumn(name = "nro_lia", referencedColumnName = "nro_lia")
    @ManyToOne
    private Elementos nroLia;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios userId;

    public Movimientos() {
    }

    public Movimientos(Integer id) {
        this.id = id;
    }

    public Movimientos(Integer id, String estado, Date fecha) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNroUnsj() {
        return nroUnsj;
    }

    public void setNroUnsj(String nroUnsj) {
        this.nroUnsj = nroUnsj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Elementos getNroLia() {
        return nroLia;
    }

    public void setNroLia(Elementos nroLia) {
        this.nroLia = nroLia;
    }

    public Usuarios getUserId() {
        return userId;
    }

    public void setUserId(Usuarios userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.martindev.inventariolia.Movimientos[ id=" + id + " ]";
    }
    
}
