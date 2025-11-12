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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
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
    
    @GET
    @Path("/{nro1}")
    @Produces(MediaType.APPLICATION_JSON)
    public Elementos getElemento(@PathParam("nro1") String nro1, @QueryParam("nro2") String nro2) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Elementos> cq = cb.createQuery(Elementos.class);
        Root<Elementos> root = cq.from(Elementos.class);

        if (nro2 != null) {
            // Si nro2 está presente, buscar por nroLia y nroUnsj
            cq.where(cb.and(
                cb.equal(root.get("elementosPK").get("nroLia"), nro1),
                cb.equal(root.get("elementosPK").get("nroUnsj"), nro2)
            ));
        } else {
            // Si nro2 no está presente, buscar por nroLia o nroUnsj
            cq.where(cb.or(
                cb.equal(root.get("elementosPK").get("nroLia"), nro1),
                cb.equal(root.get("elementosPK").get("nroUnsj"), nro1)
            ));
        }

        // Ejecutar la consulta y devolver el resultado único
        List<Elementos> resultados = em.createQuery(cq).getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}