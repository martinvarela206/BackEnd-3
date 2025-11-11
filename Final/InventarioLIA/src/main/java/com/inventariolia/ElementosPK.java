/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventariolia;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
@Embeddable
public class ElementosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nro_lia", nullable = false, length = 25)
    private String nroLia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nro_unsj", nullable = false, length = 25)
    private String nroUnsj;

    public ElementosPK() {
    }

    public ElementosPK(String nroLia, String nroUnsj) {
        this.nroLia = nroLia;
        this.nroUnsj = nroUnsj;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroLia != null ? nroLia.hashCode() : 0);
        hash += (nroUnsj != null ? nroUnsj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementosPK)) {
            return false;
        }
        ElementosPK other = (ElementosPK) object;
        if ((this.nroLia == null && other.nroLia != null) || (this.nroLia != null && !this.nroLia.equals(other.nroLia))) {
            return false;
        }
        if ((this.nroUnsj == null && other.nroUnsj != null) || (this.nroUnsj != null && !this.nroUnsj.equals(other.nroUnsj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inventariolia.ElementosPK[ nroLia=" + nroLia + ", nroUnsj=" + nroUnsj + " ]";
    }
    
}
