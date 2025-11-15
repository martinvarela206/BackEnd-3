/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martindev.inventariolia;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class MovimientosFacade extends AbstractFacade<Movimientos> {

    @PersistenceContext(unitName = "InventarioLiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientosFacade() {
        super(Movimientos.class);
    }
    
    public List<Movimientos> findByNroLia(Elementos elemento) {
        jakarta.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<Movimientos> cq = cb.createQuery(Movimientos.class);
        jakarta.persistence.criteria.Root<Movimientos> movRoot = cq.from(Movimientos.class);
        cq.select(movRoot)
            .where(cb.equal(movRoot.get("nroLia"), elemento))
            .orderBy(cb.desc(movRoot.get("fecha")));
        return em.createQuery(cq).getResultList();
    }
    
}
