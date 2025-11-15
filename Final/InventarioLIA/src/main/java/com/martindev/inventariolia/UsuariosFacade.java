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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "InventarioLiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios findByNombreYPassword(String nombre, String password) {
        try {
            jakarta.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
            jakarta.persistence.criteria.CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
            jakarta.persistence.criteria.Root<Usuarios> userRoot = cq.from(Usuarios.class);
            cq.select(userRoot)
                .where(
                    cb.and(
                        cb.equal(userRoot.get("nombre"), nombre),
                        cb.equal(userRoot.get("password"), password)
                    )
                );
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Roles> obtenerRoles(Usuarios usuario) {
        System.out.println("Roles del usuario: " + usuario.getRolesCollection());
            // Usar Criteria API para forzar la carga de roles relacionados al usuario
            jakarta.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
            jakarta.persistence.criteria.CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);
            jakarta.persistence.criteria.Root<Usuarios> userRoot = cq.from(Usuarios.class);
            jakarta.persistence.criteria.Join<Usuarios, Roles> rolesJoin = userRoot.join("rolesCollection");
            cq.select(rolesJoin).where(cb.equal(userRoot.get("id"), usuario.getId()));
            cq.distinct(true);
            return em.createQuery(cq).getResultList();
    }
}
