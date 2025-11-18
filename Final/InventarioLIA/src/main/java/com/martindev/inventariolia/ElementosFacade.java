/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martindev.inventariolia;

import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


/**
 *
 * @author Usuario
 */
@Stateless
public class ElementosFacade extends AbstractFacade<Elementos> {

    @PersistenceContext(unitName = "InventarioLiaPU")
    private EntityManager em;

    @EJB
    private MovimientosFacade movimientosFacade;
    @EJB
    private UsuariosFacade usuariosFacade; // inyectado para compatibilidad futura

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementosFacade() {
        super(Elementos.class);
    }
    
    @Override
    public void create(Elementos entity) {
        // Mantener compatibilidad: delegar a la nueva implementación sin usuario.
        create(entity, null);
    }

    public void create(Elementos entity, Usuarios usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario requerido para crear elemento y movimiento inicial");
        }
        super.create(entity);
        Movimientos m = new Movimientos();
        m.setNroLia(entity);
        m.setNroUnsj(entity.getNroUnsj());
        m.setEstado("ingresado");
        m.setUbicacion("LIA");
        m.setFecha(new Date());
        m.setComentario("Ingreso inicial");
        m.setUserId(usuario);
        movimientosFacade.create(m);
    }
    
}
