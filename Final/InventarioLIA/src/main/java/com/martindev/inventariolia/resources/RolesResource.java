package com.martindev.inventariolia.resources;

import java.util.List;

import com.martindev.inventariolia.Roles;
import com.martindev.inventariolia.RolesFacade;

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

@Path("roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolesResource {
    @EJB
    private RolesFacade rolesFacade;

    @GET
    public List<Roles> getAll() {
        return rolesFacade.findAll();
    }

    @GET
    @Path("{id}")
    public Roles getById(@PathParam("id") Integer id) {
        return rolesFacade.find(id);
    }

    @POST
    public Response create(Roles rol) {
        rolesFacade.create(rol);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Roles rol) {
        rol.setId(id);
        rolesFacade.edit(rol);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Roles rol = rolesFacade.find(id);
        if (rol != null) {
            rolesFacade.remove(rol);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
