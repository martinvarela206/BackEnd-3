package com.martindev.inventariolia.resources;

import java.util.List;

import com.martindev.inventariolia.Elementos;
import com.martindev.inventariolia.ElementosFacade;
import com.martindev.inventariolia.Movimientos;
import com.martindev.inventariolia.MovimientosFacade;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("elementos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementosResource {
    @EJB
    private ElementosFacade elementosFacade;
    @EJB
    private MovimientosFacade movimientosFacade;
    @GET
    @Path("{nroLia}/ultima-ubicacion")
    public Response getUltimaUbicacion(@PathParam("nroLia") String nroLia) {
        Elementos elemento = elementosFacade.find(nroLia);
        String ubicacion = "-";
        if (elemento != null) {
            List<Movimientos> movs = movimientosFacade.findByNroLia(elemento);
            if (movs != null && !movs.isEmpty()) {
                Movimientos ultimo = movs.get(0); // ya está ordenado por fecha desc
                if (ultimo.getUbicacion() != null) {
                    ubicacion = ultimo.getUbicacion();
                }
            }
        }
        return Response.ok(java.util.Collections.singletonMap("ubicacion", ubicacion)).build();
    }

    @GET
    public List<Elementos> getAll() {
        return elementosFacade.findAll();
    }

    @GET
    @Path("{id}")
    public Elementos getById(@PathParam("id") Integer id) {
        return elementosFacade.find(id);
    }

    @POST
    public Response create(Elementos elemento) {
        elementosFacade.create(elemento);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Elementos elemento) {
        elemento.setNroLia(String.valueOf(id));
        elementosFacade.edit(elemento);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Elementos elemento = elementosFacade.find(id);
        if (elemento != null) {
            elementosFacade.remove(elemento);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
