/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alumnosapp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
@Embeddable
public class ExamenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_idmateria", nullable = false)
    private int fkIdmateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_idalumno", nullable = false)
    private int fkIdalumno;

    public ExamenPK() {
    }

    public ExamenPK(int fkIdmateria, int fkIdalumno) {
        this.fkIdmateria = fkIdmateria;
        this.fkIdalumno = fkIdalumno;
    }

    public int getFkIdmateria() {
        return fkIdmateria;
    }

    public void setFkIdmateria(int fkIdmateria) {
        this.fkIdmateria = fkIdmateria;
    }

    public int getFkIdalumno() {
        return fkIdalumno;
    }

    public void setFkIdalumno(int fkIdalumno) {
        this.fkIdalumno = fkIdalumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkIdmateria;
        hash += (int) fkIdalumno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenPK)) {
            return false;
        }
        ExamenPK other = (ExamenPK) object;
        if (this.fkIdmateria != other.fkIdmateria) {
            return false;
        }
        if (this.fkIdalumno != other.fkIdalumno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.alumnosapp.ExamenPK[ fkIdmateria=" + fkIdmateria + ", fkIdalumno=" + fkIdalumno + " ]";
    }
    
}
