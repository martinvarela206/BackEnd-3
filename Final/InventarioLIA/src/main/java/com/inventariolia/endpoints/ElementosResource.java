package com.inventariolia.endpoints;

import com.inventariolia.Elementos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/elementos")
public class ElementosResource {
    @PersistenceContext(unitName = "InventarioLiaPU")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Elementos> getAllElementos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Elementos> cq = cb.createQuery(Elementos.class);
        Root<Elementos> root = cq.from(Elementos.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}