package com.martindev.inventariolia.resources;

import java.util.List;

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

@Path("movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientosResource {
    @EJB
    private MovimientosFacade movimientosFacade;

    @GET
    public List<Movimientos> getAll() {
        return movimientosFacade.findAll();
    }

    @GET
    @Path("{id}")
    public Movimientos getById(@PathParam("id") Integer id) {
        return movimientosFacade.find(id);
    }

    @POST
    public Response create(Movimientos movimiento) {
        movimientosFacade.create(movimiento);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Movimientos movimiento) {
        movimiento.setId(id);
        movimientosFacade.edit(movimiento);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Movimientos movimiento = movimientosFacade.find(id);
        if (movimiento != null) {
            movimientosFacade.remove(movimiento);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
